package com.didactapp.didact.entities;

public class EncryptUser {
    private String userKey;
    private String key;

    public EncryptUser(String userKey, String key) {
        this.userKey = userKey;
        this.key = key;
    }
}
