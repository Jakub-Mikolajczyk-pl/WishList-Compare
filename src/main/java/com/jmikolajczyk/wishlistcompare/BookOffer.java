package com.jmikolajczyk.wishlistcompare;

public class BookOffer {
    private final String url;
    private final Book book;

    public BookOffer(String url, Book book) {
        this.url = url;
        this.book = book;
    }

    public String getUrl() {
        return url;
    }

    public Book getBook() {
        return book;
    }
}
