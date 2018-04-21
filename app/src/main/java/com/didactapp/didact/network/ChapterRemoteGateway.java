package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.Chapter;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 12/03/2018.
 */

public class ChapterRemoteGateway implements RemoteGateway<Chapter>, Callback<List<Chapter>> {

    private static ChapterRemoteGateway INSTANCE = null;

    private WeakReference<RemoteGatewayCallback<Chapter>> callback = null;

    private ChapterRemoteGateway() {
    }


    public static ChapterRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChapterRemoteGateway();
        }
        return INSTANCE;
    }


    @Override
    public void getItemList(@NonNull RemoteGatewayCallback<Chapter> callback) {

        this.callback = new WeakReference<>(callback);
        Call<List<Chapter>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        call = apiInterface.getChapterList();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
        if (callback != null && callback.get() != null) {
            // response.isSuccessful() is true if the response code is 2xx
            if (response.isSuccessful()) {
                List<Chapter> chapterList = response.body();
                callback.get().onRemoteLoadRSuccess(chapterList);

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
    public void onFailure(Call<List<Chapter>> call, Throwable t) {
        RemoteGatewayCallback<Chapter> req = null;
        if (callback != null) {
            req = callback.get();
        }
        if (req != null) {
            req.onRemoteLoadFailed();
        }
    }

}
