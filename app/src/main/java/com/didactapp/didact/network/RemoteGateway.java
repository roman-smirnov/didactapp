package com.didactapp.didact.network;

import android.support.annotation.NonNull;

/**
 * network api gateway interface generic definition
 **/
public interface RemoteGateway<T, P> {

    void getFromRemote(@NonNull RemoteGatewayCallback<T> callback, @NonNull P... reqParams);
}