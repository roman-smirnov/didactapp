package com.didactapp.didact.network;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface RemoteGatewayCallback<T> {

    /**
     * Fetch request succeeded.
     */
    void onRemoteLoadRSuccess(T retrieved);


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
