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

    @POST("authentication/sign-in")
    Call<AuthenticationKey> login(@Body EncryptUser user);

    @GET("books/all/{key}")
    Call<List<Book>> getBookList(@Path("key") String key);

    @GET("chapters/books/{bookId}/{key}")
    Call<List<Chapter>> getChapterList(@Path("key") String key, @Path("bookId") int bookId);

    @GET("sections/chapters/{chapterId}/{key}")
    Call<List<Section>> getSectionList(@Path("key") String key, @Path("chapterId") int chapterId);


}