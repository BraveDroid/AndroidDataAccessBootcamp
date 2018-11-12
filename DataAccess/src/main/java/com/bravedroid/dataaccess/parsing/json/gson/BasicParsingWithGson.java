package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.Book;
import com.google.gson.Gson;

public class BasicParsingWithGson {
    private final Gson gson;

    public BasicParsingWithGson() {
        gson = new Gson();
    }

    public String serializeBaseModel(Book book) {
        return gson.toJson(book);
    }

    public Book deserializeBaseModel(String book) {
        return gson.fromJson(book, Book.class);
    }
}
