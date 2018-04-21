package com.didactapp.didact.contracts;

import com.didactapp.didact.entities.Section;

import java.util.List;


public interface SectionContract {

    interface View extends BaseView<Presenter> {

        void showSections(List<Section> sectionList);

        void hideSections();

        void showSectionDetailsUi(int sectionId);

        void showSpinner();

        void hideSpinner();

        void showLoadError();

        void hideLoadError();

        void showNoNetwork();

        void hideNoNetwork();

    }

    interface Presenter extends BasePresenter<View> {
        void openSectionDetails(Section requestedSection);

        void onSectionsLoaded(List<Section> sectionList);


    }

}
