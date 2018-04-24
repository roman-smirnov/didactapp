package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.didactapp.didact.entities.Chapter;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;

/**
 * an interface that should be implemented by clients that ask for Data from server
 */
public class ChapterRemoteGateway extends BaseRemoteGateway<List<Chapter>, Integer> {

    private static ChapterRemoteGateway INSTANCE = null;


    private ChapterRemoteGateway() {
    }


    public static ChapterRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChapterRemoteGateway();
        }
        return INSTANCE;
    }

    @Override
    public void getFromRemote(@NonNull RemoteGatewayCallback<List<Chapter>> callback, @NonNull Integer... reqParams) {

        this.callback = new WeakReference<>(callback);
        Call<List<Chapter>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (authKey != null && reqParams.length == 1 && reqParams[0] != null) {
            call = apiInterface.getChapterList(authKey, reqParams[0]);
            call.enqueue(this);
        }

    }


}
