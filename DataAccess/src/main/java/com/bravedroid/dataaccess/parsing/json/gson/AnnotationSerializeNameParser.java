package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.BookModel2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AnnotationSerializeNameParser {
    Gson gson;

    AnnotationSerializeNameParser() {
        gson = new Gson();
    }

    public String serializeBookModel2() {
        BookModel2 bookModel2 = new BookModel2("Gson", "Norman Peitek", 30);
        return gson.toJson(bookModel2);
    }

    public BookModel2 deserializeBookModel2(String input) {
        Type bookModelJson2 = new TypeToken<BookModel2>() {
        }.getType();
        return gson.fromJson(input, bookModelJson2);
    }
}
