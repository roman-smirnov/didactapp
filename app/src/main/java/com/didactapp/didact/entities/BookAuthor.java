package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by roman on 11/03/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = Book.class,
                parentColumns = "bookId",
                childColumns = "bookId"),
        @ForeignKey(entity = Author.class,
                parentColumns = "authorId",
                childColumns = "authorId")
})

public final class BookAuthor {
    @PrimaryKey
    private final int bookId;
    @PrimaryKey
    private final int authorId;

    public BookAuthor(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getAuthorId() {
        return authorId;
    }
}
