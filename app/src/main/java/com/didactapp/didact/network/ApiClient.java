package com.didactapp.didact.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * this class is used to instantiate the retrofit networking library and underling http client
 */
public class ApiClient {
    private static final String BASE_URL = "https://raw.githubusercontent.com/didactapp/cloud-library/master/app/sampledata/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            //we use okhttp as the http client
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

            //instantiate retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
