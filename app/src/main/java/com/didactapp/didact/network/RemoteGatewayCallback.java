package com.didactapp.didact.network;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface RemoteGatewayCallback<T> {

    /**
     * Fetch request succeeded.
     */
    void onRemoteLoadRSuccess(List<T> itemList);


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
