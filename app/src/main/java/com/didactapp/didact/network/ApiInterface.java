package com.didactapp.didact.network;

import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.Book;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.entities.EncryptUser;
import com.didactapp.didact.entities.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("authentication/public-key")
    Call<AuthenticationKey> getPublicKey();

    @POST("authentication/sign-up")
    Call<AuthenticationKey> register(@Body EncryptUser user);


    @GET("books/all/{key}")
    Call<List<Book>> getBookList(@Path("key") String key);

    @GET("chapters.json")
    Call<List<Chapter>> getChapterList();

    @GET("sections.json")
    Call<List<Section>> getSectionList();
}