package com.didactapp.didact.persistence;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.didactapp.didact.entities.Book;

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


    private BookLocalGateway() {
    }


    public static BookLocalGateway getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            initAppDatabase(context);
            INSTANCE = new BookLocalGateway();


        }
        return INSTANCE;
    }

    private static void initAppDatabase(@NonNull final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "book_database").build();
            }
        }).run();
    }


    @Override
    public void getBookList(@NonNull final BookLocalGatewayCallback callback) {
        this.callback = new WeakReference<>(callback);
        new Thread(new Runnable() {
            private volatile boolean shutdown = false;

            @Override
            public void run() {

                bookList = appDatabase.bookDao().getAll();

                if (bookList.isEmpty()) {
                    callback.onLocaleDataNotAvailable();
                } else {
                    callback.onLocalLoadRSuccess(bookList);
                }
            }
        }).start();

    }

    @Override
    public void storeBookList(final List<Book> bookList) {
        if (bookList != null && !bookList.isEmpty())
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Book book : bookList) {
                        appDatabase.bookDao().insert(book);
                    }
                }
            }).start();
    }


}
