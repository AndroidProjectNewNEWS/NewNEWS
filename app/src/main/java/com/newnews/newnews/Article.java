package com.newnews.newnews;

public class Article {
    private String title;
    private String time;
    private String author;
    private String imgUrl;
    private String imgDes;
    private String content;
    private String uid;
    private String url;

    public Article() {
        // Empty constructor is required for Firebase function.
    }

    public Article(String title, String time, String author, String imgUrl, String imgDes, String content, String uid, String url) {
        this.title = title;
        this.time = time;
        this.author = author;
        this.imgUrl = imgUrl;
        this.imgDes = imgDes;
        this.content = content;
        this.uid = uid;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getImgDes() {
        return imgDes;
    }

    public void setImgDes(String imgDes) {
        this.imgDes = imgDes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
