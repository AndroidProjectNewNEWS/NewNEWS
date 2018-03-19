package com.newnews.newnews.Data;

public class Article {
    private String title;
    private String author;
    private String publisher;

    public Article() {
        // Empty constructor is required for Firebase function.
    }

    public Article(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher=publisher;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
