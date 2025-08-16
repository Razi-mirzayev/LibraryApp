package com.library;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String status; // mövcud / götürülüb

    public Book(int id, String title, String author, String publisher, int year, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.status = status;
    }

    // Getter və Setter-lər
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getYear() { return year; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setYear(int year) { this.year = year; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("%d: %s, Müəllif: %s, Nəşriyyat: %s, İl: %d, Status: %s",
                id, title, author, publisher, year, status);
    }
}
