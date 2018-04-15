package com.didactapp.didact.presenters;

import com.didactapp.didact.contracts.SectionContract;
import com.didactapp.didact.entities.Section;

import java.util.List;

/**
 * class to handle books presentation logic
 */
public final class SectionPresenter implements SectionContract.Presenter {
    private SectionContract.View view;


    public void load() {
        view.showSpinner();
        view.hideSpinner();
    }

    @Override
    public void openSectionDetails(Section requestedSection) {
//        ???
    }

    @Override
    public void onSectionsLoaded(List<Section> sectionList) {
        view.showSections(sectionList);
    }


    @Override
    public void takeView(SectionContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
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
