package com.didactapp.didact.network.book;

import android.support.annotation.NonNull;

/**
 * Created by roman on 11/03/2018.
 */

public interface BookRemoteDataSource {

    void getBookList(@NonNull BookRemoteGatewayCallback callback);

}
