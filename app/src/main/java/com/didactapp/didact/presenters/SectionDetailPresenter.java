package com.didactapp.didact.presenters;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.contracts.SectionContract;
import com.didactapp.didact.contracts.SectionDetailContract;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.network.SectionRemoteGateway;
import com.didactapp.didact.persistence.LocalGatewayCallback;
import com.didactapp.didact.persistence.SectionLocalGateway;

import java.util.List;

import static com.didactapp.didact.utils.Constants.NO_SUCH_CHAPTER;

/**
 * class to handle books presentation logic
 */
public final class SectionDetailPresenter extends ViewModel implements SectionDetailContract.Presenter {
    private SectionDetailContract.View view = null;
    private Section section = null;

    @Override
    public void takeView(SectionDetailContract.View view) {
        if (view == null) {
            return;
        }
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void answerChosen(int choice) {

    }

    @Override
    public void loadContent() {

    }

    @Override
    public void networkDisconnected() {

    }

    @Override
    public void networkConnected() {

    }

    @Override
    public void update() {

    }
}
