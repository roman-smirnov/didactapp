package com.didactapp.didact.contracts;


public interface BasePresenter<T> {

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     */
    void takeView(T view);

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();

    void networkDisconnected();

    void networkConnected();

    void update();


}
