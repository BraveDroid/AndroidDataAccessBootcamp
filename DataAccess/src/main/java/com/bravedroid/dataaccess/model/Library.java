package com.bravedroid.dataaccess.model;

import android.support.annotation.NonNull;

import java.util.List;

public class Library {
    private String libraryAddress;
    private List<Book> listOfBooks;

    public Library(String address, List<Book> listOfBooks) {
        this.libraryAddress = address;
        this.listOfBooks = listOfBooks;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Library
                && this.libraryAddress.equals(((Library) obj).libraryAddress)
                && this.listOfBooks.containsAll(((Library) obj).listOfBooks)
                && ((Library) obj).listOfBooks.containsAll(this.listOfBooks)
                && this.listOfBooks.size() == ((Library) obj).listOfBooks.size();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
