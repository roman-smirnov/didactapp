package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;

import com.didactapp.didact.models.BookModel;


@Entity(primaryKeys = {"bookId"})
public final class Book extends BookModel {

    public Book(int bookId, String coverUrl, String thumbnailUrl, String title, String tagLine, String description, int publishedDate, int revisionDate, int version) {
        super(bookId, coverUrl, thumbnailUrl, title, tagLine, description, publishedDate, revisionDate, version);

    }


}