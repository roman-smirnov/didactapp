package com.didactapp.didact.presenters;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.didactapp.didact.contracts.SectionContract;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.network.SectionRemoteGateway;
import com.didactapp.didact.persistence.LocalGatewayCallback;
import com.didactapp.didact.persistence.SectionLocalGateway;

import java.util.List;

/**
 * class to handle books presentation logic
 */
public final class SectionPresenter extends ViewModel implements SectionContract.Presenter, RemoteGatewayCallback<Section>, LocalGatewayCallback<Section> {
    private SectionContract.View view = null;
    private SectionRemoteGateway remoteGateway;
    private SectionLocalGateway localGateway;
    private List<Section> sectionList = null;

    public SectionPresenter(@NonNull SectionRemoteGateway remoteGateway, @NonNull SectionLocalGateway localGateway) {
        this.remoteGateway = remoteGateway;
        this.localGateway = localGateway;
    }


    @Override
    public void takeView(SectionContract.View view) {
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
    public void openSectionDetails(Section requestedSection) {
//         TODO impl this
    }

    @Override
    public void onSectionsLoaded(List<Section> sectionList) {
//
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

        if (sectionList != null) {
            view.showSections(sectionList);
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
    public void onRemoteLoadRSuccess(List<Section> bookList) {
        if (view == null) {
            return;
        }
        view.hideSpinner();
        this.sectionList = bookList;
        view.showSections(bookList);
        localGateway.storeItemList(bookList);
    }

    @Override
    public void onRemoteDataNotAvailable() {
        if (view == null) {
            return;
        }

        if (sectionList != null) {
            view.showSections(sectionList);
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
        view.hideSections();
        view.hideNoNetwork();
        view.hideNoContent();
        view.hideLoadError();
        view.hideSpinner();
    }

    @Override
    public void onLocalLoadRSuccess(List<Section> bookList) {
        if (view == null) {
            return;
        }
        view.showSections(bookList);

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
