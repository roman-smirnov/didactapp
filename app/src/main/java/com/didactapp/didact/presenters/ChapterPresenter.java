package com.didactapp.didact.presenters;

import android.support.annotation.NonNull;

import com.didactapp.didact.contracts.ChapterContract;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.network.ChapterRemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.persistence.ChapterLocalGateway;
import com.didactapp.didact.persistence.LocalGatewayCallback;

import java.util.List;

/**
 * class to handle books presentation logic
 */
public final class ChapterPresenter implements ChapterContract.Presenter, RemoteGatewayCallback<Chapter>, LocalGatewayCallback<Chapter> {
    private ChapterContract.View view = null;
    private ChapterRemoteGateway remoteGateway;
    private ChapterLocalGateway localGateway;
    private List<Chapter> bookList = null;

    public ChapterPresenter(@NonNull ChapterRemoteGateway remoteGateway, @NonNull ChapterLocalGateway localGateway) {
        this.remoteGateway = remoteGateway;
        this.localGateway = localGateway;
    }


    @Override
    public void takeView(ChapterContract.View view) {
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

//    @Override
//    public void openChapterDetails(Chapter requestedChapter) {
////        TODO: implement this
////        view.showChapterDetailsUi();
//    }

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
            view.showChapters(bookList);
        } else {
            localGateway.getItemList(this);
        }

    }

    @Override
    public void networkConnected() {
        if (view == null) {
            return;
        }
        remoteGateway.getItemList(this);
    }

    @Override
    public void onRemoteLoadRSuccess(List<Chapter> bookList) {
        if (view == null) {
            return;
        }
        view.hideSpinner();
        this.bookList = bookList;
        view.showChapters(bookList);
        localGateway.storeItemList(bookList);
    }

    @Override
    public void onRemoteDataNotAvailable() {
        if (view == null) {
            return;
        }

        if (bookList != null) {
            view.showChapters(bookList);
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
        view.hideChapters();
        view.hideNoNetwork();
        view.hideNoContent();
        view.hideLoadError();
        view.hideSpinner();
    }

    @Override
    public void onLocalLoadRSuccess(List<Chapter> chapterList) {
        if (view == null) {
            return;
        }
        view.showChapters(chapterList);

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

    @Override
    public void onChapterSelected(Chapter requestedChapter) {
//        TODO: impl this
    }

    @Override
    public void loadChapters(int bookId) {
        if (bookId != -1) { // TODO: move iron number to constants
            return; // TODO: call no content instead
        }
        //        TODO: impl actual get call with bookId
        update();
    }
}
