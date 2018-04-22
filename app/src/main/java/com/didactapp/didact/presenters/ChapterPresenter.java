package com.didactapp.didact.presenters;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.contracts.ChapterContract;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.network.ChapterRemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.persistence.ChapterLocalGateway;
import com.didactapp.didact.persistence.LocalGatewayCallback;

import java.util.List;

import static com.didactapp.didact.utils.Constants.NO_SUCH_BOOK;

/**
 * class to handle books presentation logic
 */
public final class ChapterPresenter implements ChapterContract.Presenter, RemoteGatewayCallback<Chapter>, LocalGatewayCallback<Chapter> {
    private ChapterContract.View view = null;
    private ChapterRemoteGateway remoteGateway;
    private ChapterLocalGateway localGateway;
    private List<Chapter> chapterList = null;

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

        if (chapterList != null) {
            view.showChapters(chapterList);
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
    public void onRemoteLoadRSuccess(List<Chapter> chapterList) {
        if (view == null) {
            return;
        }
        view.hideSpinner();
        this.chapterList = chapterList;
        view.showChapters(chapterList);
        localGateway.storeItemList(chapterList);
        LogUtils.d("onRemoteLoadSuccess");

    }

    @Override
    public void onRemoteDataNotAvailable() {
        if (view == null) {
            return;
        }

        if (chapterList != null) {
            view.showChapters(chapterList);
        } else {
            view.showNoContent();
        }
        LogUtils.d("onRemoteDataNotAvailable");
    }

    @Override
    public void onRemoteLoadFailed() {
        view.showLoadError();
        LogUtils.d("onRemoteLoadFailed");

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
        if (bookId == NO_SUCH_BOOK || view == null) {
            hideAll();
            view.showNoContent();
            return;
        }
        //        TODO: impl actual get call with bookId
        update();
    }
}
