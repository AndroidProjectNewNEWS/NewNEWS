package com.newnews.newnews.Data;

/**
 * Created by Phil on 3/21/2018.
 */

public class ArticleTest {
    private String title;
    private String author;

    public ArticleTest(){
        // Empty constructor for Firebase
    }

    public ArticleTest(String title, String author) {
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
}
