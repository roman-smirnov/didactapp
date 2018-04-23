package com.didactapp.didact.network;

import android.support.annotation.NonNull;

/**
 * Created by roman on 11/03/2018.
 */

public interface RemoteGateway<T> {

    void getItemList(@NonNull RemoteGatewayCallback<T> callback);
}