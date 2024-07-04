package com.ite.schoolapplication.models;

public class Book {

    private int id;
    private String title;
    private String author;
    private String description;
    private String publishedDate;
    private String category;
    private byte[] image;
    private byte[] pdf;

    public Book(int id, String title, String author, String description, String publishedDate, String category, byte[] image, byte[] pdf) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedDate = publishedDate;
        this.category = category;
        this.image = image;
        this.pdf = pdf;
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

    public String getDescription() {
        return description;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getCategory() {
        return category;
    }

    public byte[] getImage() {
        return image;
    }

    public byte[] getPdf() {
        return pdf;
    }
}
