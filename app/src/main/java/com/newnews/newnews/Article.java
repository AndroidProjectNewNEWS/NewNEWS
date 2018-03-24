package com.newnews.newnews;

public class Article {
    private String title;
    private String author;
    private String imgUrl;

    public Article() {
        // Empty constructor is required for Firebase function.
    }

    public Article(String title, String author, String imgUrl) {
        this.title = title;
        this.author = author;
        this.imgUrl=imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
