package com.didactapp.didact.network;

import com.didactapp.didact.entities.Book;
import com.didactapp.didact.entities.Chapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("books.json")
    Call<List<Book>> getBookList();

    @GET("chapters.json")
    Call<List<Chapter>> getChapterList();
}