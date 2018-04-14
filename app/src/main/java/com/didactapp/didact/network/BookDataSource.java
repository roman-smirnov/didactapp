package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.didactapp.didact.network.book.BookRemoteGatewayCallback;

/**
 * Created by roman on 11/03/2018.
 */

public interface BookDataSource {

    void getBookList(@NonNull BookRemoteGatewayCallback callback);

}
