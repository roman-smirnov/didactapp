package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.EncryptUser;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by roman on 12/03/2018.
 */

public class LoginRemoteGateway extends BaseRemoteGateway<AuthenticationKey, EncryptUser> {
    private static LoginRemoteGateway INSTANCE = null;

    private LoginRemoteGateway() {
    }

    public static LoginRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRemoteGateway();
        }
        return INSTANCE;
    }

    @Override
    public void getFromRemote(@NonNull RemoteGatewayCallback<AuthenticationKey> callback, @NonNull EncryptUser... reqParams) {
        this.callback = new WeakReference<>(callback);
        Call<AuthenticationKey> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        if (reqParams.length == 1 && reqParams[0] != null) {
            call = apiInterface.login(reqParams[0]);
            call.enqueue(this);
        }

    }

    @Override
    public void onResponse(Call<AuthenticationKey> call, Response<AuthenticationKey> response) {
        super.onResponse(call, response);
        if (callback != null &&
                callback.get() != null
                && response.isSuccessful()
                && response.body() != null
                && response.body().getKey() != null) {
            authKey = response.body().getKey();
        }
    }

}
