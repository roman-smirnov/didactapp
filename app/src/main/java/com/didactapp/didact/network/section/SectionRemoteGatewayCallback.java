package com.didactapp.didact.network.section;

import com.didactapp.didact.entities.Section;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public interface SectionRemoteGatewayCallback {

    /**
     * Fetch request succeeded.
     */
    void onLoadSuccess(List<Section> sectionList);


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
