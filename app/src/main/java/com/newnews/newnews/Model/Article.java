package com.newnews.newnews.Model;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public class Article {
    private String title;
    private String author;
    private Uri imgRef;

    public Article() {
        // Empty constructor is required for Firebase function.
    }

    public Article(String title, String author, Uri imgRef) {
        this.title = title;
        this.author = author;
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

    public Uri getImgRef() {
        return imgRef;
    }

    public void setImgRef(Uri imgUrl) {
        this.imgRef = imgUrl;
    }
}
