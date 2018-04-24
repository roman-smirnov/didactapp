package com.didactapp.didact.entities;

/**
 * entity model
 */
public class EncryptUser {
    private String userKey;
    private String key;

    public EncryptUser(String userKey, String key) {
        this.userKey = userKey;
        this.key = key;
    }
}
// TODO clea nthe mess - make a model in line with rest
