package com.didactapp.didact.presenters;

import com.didactapp.didact.contracts.ChapterContract;
import com.didactapp.didact.entities.Chapter;

import java.util.List;

/**
 * class to handle books presentation logic
 */
public final class ChapterPresenter implements ChapterContract.Presenter {
    private ChapterContract.View view;


    @Override
    public void onChapterSelected(Chapter requestedChapter) {
        view.showChapterContent(requestedChapter.getChapterId());
    }

    @Override
    public void onChaptersLoaded(List<Chapter> chapterList) {
        view.showChapters(chapterList);
    }

    @Override
    public void takeView(ChapterContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void loadChapters() {
        view.showSpinner();
// do stuff
        view.hideSpinner();
    }
}
