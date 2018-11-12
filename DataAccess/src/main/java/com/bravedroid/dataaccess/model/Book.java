package com.bravedroid.dataaccess.model;

public class Book {
    private String title;
    private String author;
    private float price;
    private User user;

    public Book(String title, String author, float price, User user) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Book
                && this.author.equals(((Book) obj).author)
                && this.price == ((Book) obj).price
                && this.title.equals(((Book) obj).title)
                && this.user.equals(((Book) obj).user);
    }
}
