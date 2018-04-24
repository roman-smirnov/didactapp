package com.didactapp.didact.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.didactapp.didact.utils.Constants.BASE_API_URL;

/**
 * this class is used to instantiate the retrofit networking library and underling http client
 * these libraries are used for making network calls and converting responses to appropriate objects
 */
public class ApiClient {

    /* retrofit networking library */
    private static Retrofit retrofit = null;

    /**
     * init the networking client
     *
     * @return
     */
    public static Retrofit getClient() {
        if (retrofit == null) {

            /* we use okhttp as the http client */
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

            /* instantiate retrofit networking library */
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    /* converter used to convert json string to model objects */
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}