package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.didactapp.didact.entities.PublicKey;

import java.lang.ref.WeakReference;

import retrofit2.Call;

/**
 * an interface that should be implemented by clients that ask for Data from server
 */
public class PublicKeyRemoteGateway extends BaseRemoteGateway<PublicKey, String> {

    private static PublicKeyRemoteGateway INSTANCE = null;

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
    public void getFromRemote(@NonNull RemoteGatewayCallback<PublicKey> callback, @NonNull String... reqParams) {
        this.callback = new WeakReference<>(callback);
        Call<PublicKey> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        call = apiInterface.getPublicKey();
        call.enqueue(this);
    }
}
