package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


/**
 *
 * entity model
 *
 */
@Entity
public class UserBook {
    @PrimaryKey
    @ForeignKey(entity = Book.class, parentColumns = "bookId", childColumns = "bookId")
    private final int bookId;

    @PrimaryKey
    @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId")
    private final int userId;

    private final int progress;

    public UserBook(int bookId, int userId, int userProgress) {
        this.bookId = bookId;
        this.userId = userId;
        this.progress = userProgress;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProgress() {
        return progress;
    }
}
