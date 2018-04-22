package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.EncryptUser;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 12/03/2018.
 */

public class RegisterRemoteGateway implements AuthenticationGateway<AuthenticationKey>, Callback<AuthenticationKey> {
    private static RegisterRemoteGateway INSTANCE = null;
    private WeakReference<AuthenticationGatewayCallback<AuthenticationKey>> callback = null;

    private RegisterRemoteGateway() {
    }

    public static RegisterRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RegisterRemoteGateway();
        }
        return INSTANCE;
    }

    @Override
    public void getItem(@NonNull AuthenticationGatewayCallback<AuthenticationKey> callback) {
    }

    public void getItem(@NonNull AuthenticationGatewayCallback<AuthenticationKey> callback, EncryptUser user) {
        this.callback = new WeakReference<>(callback);
        Call<AuthenticationKey> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Gson gson = new Gson();
//        String userData = gson.toJson(user,User.class);

        call = apiInterface.register(user);
        call.enqueue(this);
    }
    // TODO: handle error cases

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
