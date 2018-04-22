package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.AuthenticationKey;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 12/03/2018.
 */

public class PublicKeyRemoteGateway implements AuthenticationGateway<AuthenticationKey>, Callback<AuthenticationKey> {

    private static PublicKeyRemoteGateway INSTANCE = null;

    private WeakReference<AuthenticationGatewayCallback<AuthenticationKey>> callback = null;

    private PublicKeyRemoteGateway() {
    }

    // TODO: HANDLE CASE OF ERRORS
    public static PublicKeyRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PublicKeyRemoteGateway();
        }
        return INSTANCE;
    }

    @Override
    public void getItem(@NonNull AuthenticationGatewayCallback<AuthenticationKey> callback) {
        this.callback = new WeakReference<>(callback);
        Call<AuthenticationKey> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        call = apiInterface.getPublicKey();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<AuthenticationKey> call, Response<AuthenticationKey> response) {
        if (callback != null && callback.get() != null) {
            // response.isSuccessful() is true if the response code is 2xx
            if (response.isSuccessful()) {
                AuthenticationKey sectionList = response.body();
                callback.get().onRemoteLoadRSuccess(sectionList);

            } else if (response.body() == null) {
                callback.get().onRemoteDataNotAvailable();
            }
        } else {
            // TODO: handle error cases
            LogUtils.d("error status code returned");
            callback.get().onRemoteLoadFailed();
        }
    }

    @Override
    public void onFailure(Call<AuthenticationKey> call, Throwable t) {
        AuthenticationGatewayCallback<AuthenticationKey> req = null;
        if (callback != null) {
            req = callback.get();
        }
        if (req != null) {
            req.onRemoteLoadFailed();
        }
    }
}
