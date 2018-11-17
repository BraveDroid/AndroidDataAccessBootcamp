package com.bravedroid.dataaccess.model;

import com.google.gson.annotations.SerializedName;

public class BookModel2 {
    private String title;
    @SerializedName("mainauthor")
    private String author;
    private int price;

    public BookModel2(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BookModel2
                && this.title.equals(((BookModel2) obj).title)
                && this.author.equals(((BookModel2) obj).author)
                && this.price == ((BookModel2) obj).price;
    }

}
