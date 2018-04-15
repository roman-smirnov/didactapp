package com.didactapp.didact.network.book;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.entities.Book;
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

public class BookRemoteGateway implements BookRemoteDataSource, Callback<List<Book>> {

    private static BookRemoteGateway INSTANCE = null;

    private WeakReference<BookRemoteGatewayCallback> callback = null;

    private BookRemoteGateway() {
    }


    public static BookRemoteGateway getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookRemoteGateway();
        }
        return INSTANCE;
    }


    @Override
    public void getBookList(@NonNull BookRemoteGatewayCallback callback) {

        this.callback = new WeakReference<>(callback);
        Call<List<Book>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        call = apiInterface.getBookList();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
        if (callback != null && callback.get() != null) {
            // response.isSuccessful() is true if the response code is 2xx
            if (response.isSuccessful()) {
                List<Book> bookList = response.body();
                callback.get().onRemoteLoadRSuccess(bookList);

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
    public void onFailure(Call<List<Book>> call, Throwable t) {
        BookRemoteGatewayCallback req = null;
        if (callback != null) {
            req = callback.get();
        }
        if (req != null) {
            req.onRemoteLoadFailed();
        }
    }

}
