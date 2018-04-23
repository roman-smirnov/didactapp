package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.Section;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;

/**
 * Created by roman on 12/03/2018.
 */

public class SectionRemoteGateway extends BaseRemoteGateway<List<Section>, Integer> {

    private static SectionRemoteGateway INSTANCE = null;

    private SectionRemoteGateway() {
    }


    public static SectionRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SectionRemoteGateway();
        }
        return INSTANCE;
    }


    @Override
    public void getFromRemote(@NonNull RemoteGatewayCallback<List<Section>> callback, @NonNull Integer... reqParams) {
        this.callback = new WeakReference<>(callback);
        Call<List<Section>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (authKey != null && reqParams.length == 1 && reqParams[0] != null) {
            call = apiInterface.getSectionList(authKey, reqParams[0]);
            call.enqueue(this);
        } else {
//            TODO: throw and handle this edge case
            LogUtils.d("ENCRYPTION KEY NOT INITIALIZED");
        }
    }

}
