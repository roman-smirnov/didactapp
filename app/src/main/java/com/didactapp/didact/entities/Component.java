package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;

import com.didactapp.didact.models.ComponentModel;


@Entity(primaryKeys = {"componentId"})
public final class Component extends ComponentModel {

    public Component(int componentId, int componentNum) {
        super(componentId, componentNum);
    }
}
