package com.didactapp.didact.network;

import android.support.annotation.NonNull;

import com.didactapp.didact.entities.Book;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;

/**
 * Created by roman on 12/03/2018.
 */

public class BookRemoteGateway extends BaseRemoteGateway<List<Book>, String> {

    private static BookRemoteGateway INSTANCE = null;

    private BookRemoteGateway() {
    }


    public static BookRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookRemoteGateway();
        }
        return INSTANCE;
    }

    @Override
    public void getFromRemote(@NonNull RemoteGatewayCallback<List<Book>> callback, @NonNull String... reqParams) {
        this.callback = new WeakReference<>(callback);
        Call<List<Book>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (authKey != null) {
            call = apiInterface.getBookList(authKey);
            call.enqueue(this);
        }
    }

}
