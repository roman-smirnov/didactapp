package com.didactapp.didact.contracts;


/**
 * chapter view/presenter contract
 */
public interface RegisterContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {


    }

}
