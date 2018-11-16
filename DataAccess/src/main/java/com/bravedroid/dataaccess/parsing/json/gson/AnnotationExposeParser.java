package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.BookModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AnnotationExposeParser {
    private Gson gson;

    public AnnotationExposeParser() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        gson = builder.create();
    }

    public String serializeBookModel() {
        BookModel bookModel = new BookModel("user story mapping", "jeff patton", 35, 8);
        return gson.toJson(bookModel);
    }

    public BookModel deserializeBookModel(String bookModel) {
        Type bookModelJson = new TypeToken<BookModel>() {
        }.getType();
        return gson.fromJson(bookModel, bookModelJson);
    }
}
