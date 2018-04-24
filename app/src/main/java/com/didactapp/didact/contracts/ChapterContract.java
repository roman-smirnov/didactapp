package com.didactapp.didact.contracts;

import com.didactapp.didact.entities.Chapter;

import java.util.List;

/**
 * chapter view/presenter contract
 */
public interface ChapterContract {

    interface View extends BaseView<Presenter> {

        void showChapters(List<Chapter> chapterList);

        void hideChapters();

        void showChapterContent(int chapterId);


    }

    interface Presenter extends BasePresenter<View> {
        void onChapterSelected(Chapter requestedChapter);

        void loadChapters(int bookId);

    }

}
