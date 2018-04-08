package com.didactapp.didact.network.chapter;

import android.support.annotation.NonNull;

/**
 * Created by roman on 11/03/2018.
 */

public interface ChapterDataSource {

    void getChapterList(@NonNull ChapterRemoteGatewayCallback callback);


}
