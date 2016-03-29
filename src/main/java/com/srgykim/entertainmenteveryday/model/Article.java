package com.srgykim.entertainmenteveryday.model;

public class Article {

    private int articleId;
    private String title;
    private String shortTitledId;
    private String publicationDate;
    private String articleContent;
    private String articleExtract;
    private int categoryId;
    private String categoryName;
    private String authorId;
    private String authorName;

    public Article() {}

    public Article(int articleId, String title, String shortTitledId,
                   String publicationDate, String articleContent,
                   String articleExtract, int categoryId,
                   String categoryName, String authorId, String authorName) {

        this.articleId = articleId;
        this.title = title;
        this.shortTitledId = shortTitledId;
        this.publicationDate = publicationDate;
        this.articleContent = articleContent;
        this.articleExtract = articleExtract;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public void setAllFields(int articleId, String title, String shortTitledId,
                             String publicationDate, String articleContent,
                             String articleExtract, int categoryId,
                             String categoryName, String authorId, String authorName) {

        this.articleId = articleId;
        this.title = title;
        this.shortTitledId = shortTitledId;
        this.publicationDate = publicationDate;
        this.articleContent = articleContent;
        this.articleExtract = articleExtract;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitledId() {
        return shortTitledId;
    }

    public void setShortTitledId(String shortTitledId) {
        this.shortTitledId = shortTitledId;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleExtract() {
        return articleExtract;
    }

    public void setArticleExtract(String articleExtract) {
        this.articleExtract = articleExtract;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
