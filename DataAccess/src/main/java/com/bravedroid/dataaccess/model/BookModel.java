package com.bravedroid.dataaccess.model;

import com.google.gson.annotations.Expose;

public class BookModel {
    @Expose(serialize = false, deserialize = false)
    public String title = "";
    @Expose()
    public String author;
    @Expose()
    public float price;
    @Expose(serialize = false, deserialize = false)
    public int hoursOfWork;

    public BookModel(String title, String author, float price, int hoursOfWork) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.hoursOfWork = hoursOfWork;
    }
}
