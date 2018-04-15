package com.didactapp.didact.network.book;

import com.didactapp.didact.entities.Book;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface BookRemoteGatewayCallback {

    /**
     * Fetch request succeeded.
     */
    void onRemoteLoadRSuccess(List<Book> bookList);


    /**
     * Fetch request succeeded - but no data to show
     */
    void onRemoteDataNotAvailable();

    /**
     * Fetch request failed.
     *
     * @param error
     */
    void onRemoteLoadFailed();

}
