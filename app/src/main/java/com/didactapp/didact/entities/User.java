package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;

import com.didactapp.didact.models.UserModel;


@Entity(primaryKeys = {"userId"})
public final class User extends UserModel {

    public User(int userId, int userPoints) {
        super(userId, userPoints);
    }
}
