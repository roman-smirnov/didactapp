package com.didactapp.didact.contracts;

import com.didactapp.didact.entities.Chapter;

import java.util.List;


public interface ChapterContract {

    interface View extends BaseView<Presenter> {

        void showChapters(List<Chapter> chapterList);

        void hideChapters();

        void showChapterContent(int chapterId);

        void showSpinner();

        void hideSpinner();

        void showLoadError();

        void hideLoadError();

        void showNoNetwork();

        void hideNoNetwork();

    }

    interface Presenter extends BasePresenter<View> {
        void onChapterSelected(Chapter requestedChapter);

        void onChaptersLoaded(List<Chapter> chapterList);

        void loadChapters();


    }

}
