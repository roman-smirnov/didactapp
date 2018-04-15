package com.didactapp.didact.persistence.book;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;

import com.didactapp.didact.entities.Book;
import com.didactapp.didact.utils.AppThreadPoolExecutor;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by roman on 12/03/2018.
 */

public class BookLocalGateway implements BookLocalDataSource {

    private static BookLocalGateway INSTANCE = null;
    private static AppDatabase appDatabase = null;
    private List<Book> bookList = null;
    private WeakReference<BookLocalGatewayCallback> callback = null;
    private static AppThreadPoolExecutor executor = null;

    private BookLocalGateway() {
    }


    public static BookLocalGateway getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            executor = new AppThreadPoolExecutor();
            initAppDatabase(context);
            INSTANCE = new BookLocalGateway();


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
    public void getBookList(@NonNull final BookLocalGatewayCallback callback) {
        this.callback = new WeakReference<>(callback);
        executor.diskIO().execute(new Runnable() {
            @Override
            public void run() {

                bookList = appDatabase.bookDao().getAll();

                executor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (bookList.isEmpty()) {
                            callback.onLocaleDataNotAvailable();
                        } else {
                            callback.onLocalLoadRSuccess(bookList);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void storeBookList(final List<Book> bookList) {
        if (bookList != null && !bookList.isEmpty())
            executor.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    for (Book book : bookList) {
                        try {
                            appDatabase.bookDao().insert(book);
                        } catch (SQLiteConstraintException e) {
//                            TODO: DON'T EVER CATCH AND DO NOTHING!
                        }
                    }
                }
            });
    }


}
