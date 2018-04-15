package com.didactapp.didact.persistence;

import com.didactapp.didact.entities.Book;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface BookLocalGatewayCallback {

    /**
     * Fetch request succeeded.
     */
    void onLocalLoadRSuccess(List<Book> bookList);


    /**
     * Fetch request succeeded - but no data to show
     */
    void onLocaleDataNotAvailable();

    /**
     * Fetch request failed.
     *
     * @param error
     */
    void onLocalLoadFailed();

}
