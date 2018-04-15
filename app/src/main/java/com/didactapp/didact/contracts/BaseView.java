package com.didactapp.didact.contracts;


public interface BaseView<T> {

    /* show/hide loading spinner */

    void showSpinner();

    void hideSpinner();

    /* show/hide network loading error */

    void showLoadError();

    void hideLoadError();

    /* show/hide network connection not available message */

    void showNoNetwork();

    void hideNoNetwork();

    /* show/hide no content placeholder */

    void showNoContent();

    void hideNoContent();

    /* framework/platform utilities */

    void checkNetworkState();

}
