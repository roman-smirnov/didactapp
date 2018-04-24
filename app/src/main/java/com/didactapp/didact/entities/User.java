package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;

import com.didactapp.didact.models.UserModel;


/**
 * entity model
 */
@Entity(primaryKeys = {"userId"})
public final class User extends UserModel {
    public User(String email, String password) {
        super(email, password);
    }
}
