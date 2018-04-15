package com.didactapp.didact.persistence;

import android.support.annotation.NonNull;

import com.didactapp.didact.entities.Book;

import java.util.List;

/**
 * Created by roman on 11/03/2018.
 */

public interface BookLocalDataSource {

    void getBookList(@NonNull BookLocalGatewayCallback callback);

    void storeBookList(List<Book> bookList);

}
