package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.didactapp.didact.models.ChapterModel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"chapterId"}, foreignKeys = @ForeignKey(entity = Book.class,
        parentColumns = "bookId",
        childColumns = "chapterId",
        onDelete = CASCADE))
public final class Chapter extends ChapterModel {
    public Chapter(int chapterId, int bookId, int chapterNum, String name, String description, String thumbnailUrl) {
        super(chapterId, bookId, chapterNum, name, description, thumbnailUrl);
    }
}

