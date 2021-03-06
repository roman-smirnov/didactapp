package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.didactapp.didact.models.SectionModel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * entity model
 */
@Entity(primaryKeys = {"sectionId"},
        foreignKeys = @ForeignKey(entity = Chapter.class,
                parentColumns = "chapterId",
                childColumns = "sectionId",
                onDelete = CASCADE))
public final class Section extends SectionModel {

    public Section(int sectionId, int chapterId, int sectionNum, String name,
                   String explanation, String imageUrl, String question,
                   String wrongAnswer1, String wrongAnswer2, String wrongAnswer3,
                   String correctAnswer) {

        super(sectionId, chapterId, sectionNum, name, explanation, imageUrl
                , question, wrongAnswer1, wrongAnswer2, wrongAnswer3, correctAnswer);
    }
}
