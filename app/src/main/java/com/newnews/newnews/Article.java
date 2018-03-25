package com.newnews.newnews;

public class Article {
    private String title;
    private String author;
    private String bodyImgUrl;
    private String entryImgUrl;
    private String content;
    private String time;
    private String uid;
    private String bodyImgDes;

    public Article() {
        // Empty constructor is required for Firebase function.
    }

    public Article(String title, String author, String bodyImgUrl, String entryImgUrl, String content, String time, String uid, String bodyImgDes) {
        this.title = title;
        this.author = author;
        this.bodyImgUrl = bodyImgUrl;
        this.entryImgUrl = entryImgUrl;
        this.content = content;
        this.time = time;
        this.uid = uid;
        this.bodyImgDes = bodyImgDes;
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


    public String getBodyImgUrl() {
        return bodyImgUrl;
    }


    public String getEntryImgUrl() {
        return entryImgUrl;
    }


    public String getContent() {
        return content;
    }


    public String getTime() {
        return time;
    }

    /*
     public void setAuthor(String author) {
        this.author = author;
    }
        public void setBodyImgUrl(String bodyImgUrl) {
        this.bodyImgUrl = bodyImgUrl;
    }
    public void setEntryImgUrl(String entryImaUrl) {
        this.entryImgUrl = entryImaUrl;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
 public void setBodyImgDes(String bodyImgDes) {
        this.bodyImgDes = bodyImgDes;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setContent(String content) {
        this.content = content;
    }*/


    public String getUid() {
        return uid;
    }


    public String getBodyImgDes() {
        return bodyImgDes;
    }


}
