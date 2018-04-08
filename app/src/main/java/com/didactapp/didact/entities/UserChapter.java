package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by roman on 11/03/2018.
 */

@Entity
public class UserChapter {
    @PrimaryKey
    @ForeignKey(entity = Chapter.class, parentColumns = "chapterId", childColumns = "chapterId")
    private final int chapterId;

    @PrimaryKey
    @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId")
    private final int userId;

    private final int progress;

    public UserChapter(int chapterId, int userId, int userProgress) {
        this.chapterId = chapterId;
        this.userId = userId;
        this.progress = userProgress;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProgress() {
        return progress;
    }
}
