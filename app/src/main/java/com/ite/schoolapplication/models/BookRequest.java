package com.ite.schoolapplication.models;

public class BookRequest {
    private int id;
    private String title;
    private String author;
    private String publishedDate;
    private String link;
    private String reason;

    public BookRequest(int id, String title, String author, String publishedDate, String link, String reason) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.link = link;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getLink() {
        return link;
    }

    public String getReason() {
        return reason;
    }
}
