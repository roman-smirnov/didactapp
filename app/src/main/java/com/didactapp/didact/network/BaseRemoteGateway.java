package com.didactapp.didact.network;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * an interface that should be implemented by clients that ask for Data from {@code ApiManager}
 */
public abstract class BaseRemoteGateway<T, P> implements RemoteGateway<T, P>, Callback<T> {
    static String authKey = null;
    WeakReference<RemoteGatewayCallback<T>> callback = null;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (callback != null && callback.get() != null) {
            /* response.isSuccessful() is true if the response code is 2xx */
            if (response.isSuccessful()) {
                T retrieved = response.body();
                callback.get().onRemoteLoadRSuccess(retrieved);
            } else if (response.body() == null) {
                callback.get().onRemoteDataNotAvailable();
            } else {
                callback.get().onRemoteLoadFailed();
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RemoteGatewayCallback<T> req = null;
        if (callback != null) {
            req = callback.get();
        }
        if (req != null) {
            req.onRemoteLoadFailed();
        }
    }
}
