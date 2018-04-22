package com.didactapp.didact.network;

import com.didactapp.didact.entities.Book;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.entities.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("books.json")
    Call<List<Book>> getBookList();

    @GET("chapters.json")
    Call<List<Chapter>> getChapterList();

    @GET("sections.json")
    Call<List<Section>> getSectionList();
}