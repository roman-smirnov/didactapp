package com.didactapp.didact.models;

public abstract class AuthorModel {

    protected final int authorId;
    protected final String firstName;
    protected final String lastName;
    protected final String about;

    protected AuthorModel(int authorId, String firstName, String lastName, String about) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
    }

    public final int getAuthorId() {
        return authorId;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getAbout() {
        return about;
    }

    @Override
    public String toString() {
        return "AuthorModel{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
