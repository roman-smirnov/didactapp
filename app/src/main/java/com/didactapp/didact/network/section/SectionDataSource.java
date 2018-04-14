package com.didactapp.didact.network.section;

import android.support.annotation.NonNull;

/**
 * Created by roman on 11/03/2018.
 */

public interface SectionDataSource {

    void getSectionList(@NonNull SectionRemoteGatewayCallback callback);


}
