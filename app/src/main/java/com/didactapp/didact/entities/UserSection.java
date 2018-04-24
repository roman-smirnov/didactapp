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
public class UserSection {
    @PrimaryKey
    @ForeignKey(entity = Section.class, parentColumns = "sectionId", childColumns = "sectionId")
    private final int sectionId;

    @PrimaryKey
    @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId")
    private final int userId;

    private final int progress;

    public UserSection(int sectionId, int userId, int userProgress) {
        this.sectionId = sectionId;
        this.userId = userId;
        this.progress = userProgress;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProgress() {
        return progress;
    }
}
