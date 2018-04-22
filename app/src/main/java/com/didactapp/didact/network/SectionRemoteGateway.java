package com.didactapp.didact.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.Section;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 12/03/2018.
 */

public class SectionRemoteGateway implements RemoteGateway<Section>, Callback<List<Section>> {

    private static SectionRemoteGateway INSTANCE = null;

    private WeakReference<RemoteGatewayCallback<Section>> callback = null;

    private SectionRemoteGateway() {
    }


    public static SectionRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SectionRemoteGateway();
        }
        return INSTANCE;
    }

    @Override
    public void getItemList(@NonNull RemoteGatewayCallback<Section> callback) {
        this.callback = new WeakReference<>(callback);
        Call<List<Section>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        call = apiInterface.getSectionList();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
        if (callback != null && callback.get() != null) {
            // response.isSuccessful() is true if the response code is 2xx
            if (response.isSuccessful()) {
                List<Section> sectionList = response.body();
                callback.get().onRemoteLoadRSuccess(sectionList);
            } else if (response.body() == null || response.body().isEmpty()) {
                callback.get().onRemoteDataNotAvailable();
            }
        } else {
            // TODO: handle error cases
            LogUtils.d("error status code returned");
            callback.get().onRemoteLoadFailed();
        }
    }

    @Override
    public void onFailure(Call<List<Section>> call, Throwable t) {
        RemoteGatewayCallback<Section> req = null;
        if (callback != null) {
            req = callback.get();
        }
        if (req != null) {
            req.onRemoteLoadFailed();
        }
    }

}
