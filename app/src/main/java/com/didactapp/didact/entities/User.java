package com.didactapp.didact.entities;

import com.didactapp.didact.models.UserModel;


//@Entity(primaryKeys = {"userId"})
public final class User extends UserModel {
    public User(String email, String password) {
        super(email, password);
    }
}
