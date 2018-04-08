package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;


import com.didactapp.didact.models.SectionModel;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(primaryKeys = {"sectionId"},
        foreignKeys = @ForeignKey(entity = Chapter.class,
                parentColumns = "chapterId",
                childColumns = "sectionId",
                onDelete = CASCADE))
public final class Section extends SectionModel {

    public Section(int sectionId, int chapterId, int sectionNum) {
        super(sectionId, chapterId, sectionNum);
    }
}
