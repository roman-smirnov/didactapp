package com.didactapp.didact.persistence;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;

import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.utils.AppThreadPoolExecutor;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by roman on 12/03/2018.
 */

public class ChapterLocalGateway implements LocalGateway<Chapter> {

    private static ChapterLocalGateway INSTANCE = null;
    private static AppDatabase appDatabase = null;
    private static AppThreadPoolExecutor executor = null;
    private List<Chapter> chapterList = null;
    private WeakReference<LocalGatewayCallback<Chapter>> callback = null;

    private ChapterLocalGateway() {
    }


    public static ChapterLocalGateway getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            executor = new AppThreadPoolExecutor();
            initAppDatabase(context);
            INSTANCE = new ChapterLocalGateway();


        }
        return INSTANCE;
    }

    private static void initAppDatabase(@NonNull final Context context) {

        executor.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "book_database").build();
            }
        });
    }


    @Override
    public void getItemList(@NonNull final LocalGatewayCallback<Chapter> callback) {
        this.callback = new WeakReference<>(callback);
        executor.diskIO().execute(new Runnable() {
            @Override
            public void run() {

                chapterList = appDatabase.chapterDao().getAll();

                executor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (chapterList.isEmpty()) {
                            callback.onLocaleDataNotAvailable();
                        } else {
                            callback.onLocalLoadRSuccess(chapterList);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void storeItemList(final List<Chapter> chapterList) {
        if (chapterList != null && !chapterList.isEmpty())
            executor.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    //                TODO: rewrite database insert list via single transaction
                    for (Chapter chapter : chapterList) {
                        try {
                            appDatabase.chapterDao().insert(chapter);
                        } catch (SQLiteConstraintException e) {
//                            TODO: DON'T EVER CATCH AND DO NOTHING!
                        }
                    }
                }
            });
    }


}
