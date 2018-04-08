package com.didactapp.didact.network.chapter;

import com.didactapp.didact.entities.Chapter;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface ChapterRemoteGatewayCallback {

    /**
     * Fetch request succeeded.
     */
    void onLoadSuccess(List<Chapter> chapterList);


    /**
     * Fetch request succeeded - but no data to show
     */
    void onDataNotAvailable();

    /**
     * Fetch request failed.
     *
     * @param error
     */
    void onLoadFailed();

}
