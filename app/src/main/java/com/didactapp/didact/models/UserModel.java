package com.didactapp.didact.models;

/**
 * domain model
 */
public abstract class UserModel {

    protected final String email;
    protected final String password;

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
