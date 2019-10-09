package com.jmikolajczyk.wishlistcompare;

import java.util.List;

public interface BookFinder {
    List<BookOffer> findBooks(List<Book> bookRequests);
}
