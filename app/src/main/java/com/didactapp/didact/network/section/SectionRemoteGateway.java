package com.didactapp.didact.network.section;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.network.ApiClient;
import com.didactapp.didact.network.ApiInterface;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 12/03/2018.
 */

public class SectionRemoteGateway implements SectionDataSource, Callback<List<Section>> {

    private static SectionRemoteGateway INSTANCE = null;

    private WeakReference<SectionRemoteGatewayCallback> callback = null;

    private SectionRemoteGateway() {
    }


    public static SectionRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SectionRemoteGateway();
        }
        return INSTANCE;
    }


    @Override
    public void getSectionList(@NonNull SectionRemoteGatewayCallback callback) {

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
                callback.get().onLoadSuccess(sectionList);

            } else if (response.body() == null || response.body().isEmpty()) {
                callback.get().onDataNotAvailable();
            }
        } else {
            // TODO: handle error cases
            LogUtils.d("error status code returned");
            callback.get().onLoadFailed();
        }
    }

    @Override
    public void onFailure(Call<List<Section>> call, Throwable t) {
        SectionRemoteGatewayCallback req = null;
        if (callback != null) {
            req = callback.get();
        }
        if (req != null) {
            req.onLoadFailed();
        }
    }

}
