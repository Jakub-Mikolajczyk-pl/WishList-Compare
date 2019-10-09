package com.jmikolajczyk.wishlistcompare;

public class Book {
    private final String author;
    private final String title;
    private final String ISBN;

    //Setter and Getter methods for class Book
    public Book(String author, String title, String ISBN) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }
}
