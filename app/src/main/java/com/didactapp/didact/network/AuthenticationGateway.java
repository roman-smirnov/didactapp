package com.didactapp.didact.network;

import android.support.annotation.NonNull;

/**
 * Created by roman on 11/03/2018.
 */

public interface AuthenticationGateway<T> {

    void getItem(@NonNull AuthenticationGatewayCallback<T> callback);

}
