package com.didactapp.didact.persistence;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.utils.AppThreadPoolExecutor;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from disk
 **/
public class SectionLocalGateway implements LocalGateway<Section> {

    private static SectionLocalGateway INSTANCE = null;
    private static AppDatabase appDatabase = null;
    private static AppThreadPoolExecutor executor = null;
    private List<Section> sectionList = null;
    private WeakReference<LocalGatewayCallback<Section>> callback = null;

    private SectionLocalGateway() {
    }


    public static SectionLocalGateway getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            executor = new AppThreadPoolExecutor();
            initAppDatabase(context);
            INSTANCE = new SectionLocalGateway();


        }
        return INSTANCE;
    }

    private static void initAppDatabase(@NonNull final Context context) {

        executor.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, context.getString(R.string.database_name)).build();
            }
        });
    }


    @Override
    public void getItemList(@NonNull final LocalGatewayCallback<Section> callback) {
        this.callback = new WeakReference<>(callback);
        executor.diskIO().execute(new Runnable() {
            @Override
            public void run() {

                sectionList = appDatabase.sectionDao().getAll();

                executor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (sectionList.isEmpty()) {
                            callback.onLocaleDataNotAvailable();
                        } else {
                            callback.onLocalLoadRSuccess(sectionList);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void storeItemList(final List<Section> sectionList) {
        if (sectionList != null && !sectionList.isEmpty())
            executor.diskIO().execute(new Runnable() {
                @Override
                public void run() {
//                    TODO: implement a single transaction instead of multiple
                    for (Section section : sectionList) {
                        try {
                            appDatabase.sectionDao().insert(section);
                        } catch (SQLiteConstraintException e) {
//                            TODO: DON'T EVER CATCH AND DO NOTHING!
                        }
                    }
                }
            });
    }


}
