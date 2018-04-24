package com.didactapp.didact.presenters;
// TODO: impl this
//
//import android.arch.lifecycle.ViewModel;
//import android.support.annotation.NonNull;
//
//import com.apkfuns.logutils.LogUtils;
//import com.didactapp.didact.contracts.SectionContract;
//import com.didactapp.didact.entities.Section;
//import com.didactapp.didact.network.RemoteGatewayCallback;
//import com.didactapp.didact.network.SectionRemoteGateway;
//import com.didactapp.didact.persistence.LocalGatewayCallback;
//import com.didactapp.didact.persistence.SectionLocalGateway;
//
//import java.util.List;
//
//import static com.didactapp.didact.utils.Constants.NO_SUCH_CHAPTER;
//
///**
// * class to handle books presentation logic
// */
//public final class LoginPresenter implements SectionContract.Presenter, RemoteGatewayCallback<Section>, LocalGatewayCallback<Section> {
//    private SectionContract.View view = null;
//    private SectionRemoteGateway remoteGateway;
//    private SectionLocalGateway localGateway;
//    private List<Section> sectionList = null;
//
//    public LoginPresenter(@NonNull SectionRemoteGateway remoteGateway, @NonNull SectionLocalGateway localGateway) {
//        this.remoteGateway = remoteGateway;
//        this.localGateway = localGateway;
//    }
//
//
//    @Override
//    public void takeView(SectionContract.View view) {
//        if (view == null) {
//            return;
//        }
//        this.view = view;
//    }
//
//    @Override
//    public void dropView() {
//        view = null;
//    }
//
//
//    @Override
//    public void update() {
//        if (view == null) {
//            return;
//        }
//        hideAll();
//        LogUtils.d("update");
//        view.showSpinner();
//        view.checkNetworkState();
//
//    }
//
//    @Override
//    public void networkDisconnected() {
//        if (view == null) {
//            return;
//        }
//
//        view.hideSpinner();
//        view.showNoNetwork();
//
//        if (sectionList != null) {
//            view.showSections(sectionList);
//        } else {
//            localGateway.getFromRemote(this);
//        }
//
//    }
//
//    @Override
//    public void networkConnected() {
//        if (view == null) {
//            return;
//        }
//        remoteGateway.getFromRemote(this);
//    }
//
//    @Override
//    public void onRemoteLoadRSuccess(List<Section> sectionList) {
//        if (view == null) {
//            return;
//        }
//        this.sectionList = sectionList;
//        view.showSections(sectionList);
//        localGateway.storeItemList(sectionList);
//        view.hideSpinner();
//    }
//
//    @Override
//    public void onRemoteDataNotAvailable() {
//        if (view == null) {
//            return;
//        }
//        view.hideSpinner();
//        if (sectionList != null) {
//            view.showSections(sectionList);
//        } else {
//            view.showNoContent();
//        }
//        LogUtils.d("onRemoteDataNotAvailable");
//    }
//
//    @Override
//    public void onRemoteLoadFailed() {
//        view.showLoadError();
//        view.hideSpinner();
//        LogUtils.d("onRemoteLoadFailed");
//
//    }
//
//    private void hideAll() {
//        if (view == null) {
//            return;
//        }
//        view.hideSections();
//        view.hideNoNetwork();
//        view.hideNoContent();
//        view.hideLoadError();
//        view.hideSpinner();
//    }
//
//    @Override
//    public void onLocalLoadRSuccess(List<Section> sectionList) {
//        if (view == null) {
//            return;
//        }
//        view.hideSpinner();
//        view.showSections(sectionList);
//
//    }
//
//    @Override
//    public void onLocaleDataNotAvailable() {
//        if (view == null) {
//            return;
//        }
//        view.hideSpinner();
//        view.showNoContent();
//    }
//
//    @Override
//    public void onLocalLoadFailed() {
//        if (view == null) {
//            return;
//        }
//        view.hideSpinner();
//        view.showNoContent();
//    }
//
//    @Override
//    public void onSectionSelected(Section requestedSection) {
////        TODO: impl this
//    }
//
//    @Override
//    public void loadSections(int bookId) {
//        if (bookId == NO_SUCH_CHAPTER || view == null) {
//            hideAll();
//            view.showNoContent();
//            return;
//        }
//        //        TODO: impl actual get call with bookId
//        update();
//    }
//}
