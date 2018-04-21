package com.didactapp.didact.persistence;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface LocalGatewayCallback<T> {

    /**
     * Fetch request succeeded.
     */
    void onLocalLoadRSuccess(List<T> itemList);


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
