package com.newnews.newnews.Data;

import java.util.Date;

public class Article {
    private String imgUrl;
    private String title;
    private String author;
    private String organization;
    private String content;
    private Date dateCreated;

    public Article() {
        // Empty constructor is required for Firebase function.
    }

    public Article(String imgUrl, String title, String author, String organization, String content, Date dateCreated) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.author = author;
        this.organization = organization;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
