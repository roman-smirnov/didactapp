package com.didactapp.didact.entities;

import android.arch.persistence.room.Entity;

import com.didactapp.didact.models.AuthorModel;


/**
 * entity model
 */
@Entity(primaryKeys = {"authorId"})
public final class Author extends AuthorModel {

    public Author(int authorId, String firstName, String lastName, String about) {
        super(authorId, firstName, lastName, about);
    }
}
