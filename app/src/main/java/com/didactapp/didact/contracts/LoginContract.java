package com.didactapp.didact.contracts;


/**
 * view/presenter contract
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {


    }

}
