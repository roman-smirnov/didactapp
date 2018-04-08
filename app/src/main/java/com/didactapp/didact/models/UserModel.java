package com.didactapp.didact.models;

public abstract class UserModel {

    protected final int userId;
    protected final int userPoints;

    protected UserModel(int userId, int userPoints) {
        this.userId = userId;
        this.userPoints = userPoints;
    }

    public final int getUserId() {
        return userId;
    }

    public final int getUserPoints() {
        return userPoints;
    }
}
