package com.srgykim.entertainmenteveryday.model;

/**
 * Class Author represents an author.
 */
public class Author {
    private String authorId;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;

    public Author() {}

    public Author(String authorId, String password, String firstName,
                  String middleName, String lastName) {

        this.authorId = authorId;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void setAllFields(String authorId, String password, String firstName,
                             String middleName, String lastName) {

        this.authorId = authorId;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
