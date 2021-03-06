package com.didactapp.didact.contracts;

import com.didactapp.didact.entities.Section;

import java.util.List;


/**
 * chapter view/presenter contract
 */
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
        void onSectionSelected(Section requestedSection);

        void loadSections(int chapterId);


    }

}
