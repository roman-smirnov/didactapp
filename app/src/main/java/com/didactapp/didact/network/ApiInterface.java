package com.didactapp.didact.network;

import com.didactapp.didact.entities.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("books.json")
    Call<List<Book>> getBookList();
}