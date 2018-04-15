package com.didactapp.didact.presenters;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.didactapp.didact.contracts.LibraryContract;
import com.didactapp.didact.entities.Book;
import com.didactapp.didact.network.book.BookRemoteGateway;
import com.didactapp.didact.network.book.BookRemoteGatewayCallback;
import com.didactapp.didact.persistence.BookLocalGateway;
import com.didactapp.didact.persistence.BookLocalGatewayCallback;

import java.util.List;

/**
 * class to handle books presentation logic
 */
public final class LibraryPresenter extends ViewModel implements LibraryContract.Presenter, BookRemoteGatewayCallback, BookLocalGatewayCallback {
    private LibraryContract.View view = null;
    private BookRemoteGateway remoteGateway;
    private BookLocalGateway localGateway;
    private List<Book> bookList = null;

    public LibraryPresenter(@NonNull BookRemoteGateway remoteGateway, @NonNull BookLocalGateway localGateway) {
        this.remoteGateway = remoteGateway;
        this.localGateway = localGateway;
    }


    @Override
    public void takeView(LibraryContract.View view) {
        if (view == null) {
            return;
        }
        this.view = view;
        update();
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void openBookDetails(Book requestedBook) {
//        TODO: implement this
//        view.showBookDetailsUi();
    }

    @Override
    public void update() {
        if (view == null) {
            return;
        }
        hideAll();
        view.showSpinner();
        view.checkNetworkState();

    }

    @Override
    public void networkDisconnected() {
        if (view == null) {
            return;
        }

        view.hideSpinner();
        view.showNoNetwork();

        if (bookList != null) {
            view.showBooks(bookList);
        } else {
            localGateway.getBookList(this);
        }

    }

    @Override
    public void networkConnected() {
        if (view == null) {
            return;
        }
        remoteGateway.getBookList(this);
    }

    @Override
    public void onRemoteLoadRSuccess(List<Book> bookList) {
        if (view == null) {
            return;
        }
        view.hideSpinner();
        this.bookList = bookList;
        view.showBooks(bookList);
        localGateway.storeBookList(bookList);
    }

    @Override
    public void onRemoteDataNotAvailable() {
        if (view == null) {
            return;
        }

        if (bookList != null) {
            view.showBooks(bookList);
        } else {
            view.showNoContent();
        }
    }

    @Override
    public void onRemoteLoadFailed() {
        view.showLoadError();
    }

    private void hideAll() {
        if (view == null) {
            return;
        }
        view.hideBooks();
        view.hideNoNetwork();
        view.hideNoContent();
        view.hideLoadError();
        view.hideSpinner();
    }

    @Override
    public void onLocalLoadRSuccess(List<Book> bookList) {
        if (view == null) {
            return;
        }
        view.showBooks(bookList);

    }

    @Override
    public void onLocaleDataNotAvailable() {
        if (view == null) {
            return;
        }
        view.showNoContent();
    }

    @Override
    public void onLocalLoadFailed() {
        if (view == null) {
            return;
        }
        view.showNoContent();
    }
}
