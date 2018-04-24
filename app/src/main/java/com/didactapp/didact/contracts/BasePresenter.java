package com.didactapp.didact.contracts;


/**
 * base presenter interface - template for implementing presentation and business logic handlers
 *
 * @param <T>
 */
public interface BasePresenter<T> {

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     */
    void takeView(T view);

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();

    /**
     *  handle no network connection
     */
    void networkDisconnected();

    /**
     *  called when network is connected
     */
    void networkConnected();

    /**
     *  view update requested
     */
    void update();

}
