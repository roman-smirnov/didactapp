package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by roman on 11/03/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = Section.class,
                parentColumns = "sectionId",
                childColumns = "sectionId"),
        @ForeignKey(entity = Component.class,
                parentColumns = "componentId",
                childColumns = "componentId")
})

public class SectionComponent {
    @PrimaryKey
    private final int sectionId;
    @PrimaryKey
    private final int componentId;

    public SectionComponent(int sectionId, int componentId) {
        this.sectionId = sectionId;
        this.componentId = componentId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getComponentId() {
        return componentId;
    }
}
