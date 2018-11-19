package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.Book;
import com.bravedroid.dataaccess.model.Library;
import com.bravedroid.dataaccess.model.User;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

class MappingArraysAndListsOfObjects {
    private Gson gson = new Gson();

    public String mapListSerialisation() {
        List<Book> listOfBooks = new ArrayList<>();
        User user1 = new User(20, "sam", "spenser");
        User user2 = new User(29, "guess", "white");
        User user3 = new User(28, "shon", "naser");
        User user4 = new User(33, "july", "lasiter");

        listOfBooks.add(new Book("User story Mapping", "Jeff Patten", 35, user1));
        listOfBooks.add(new Book("Streaming Systems", "Tyler Akidau, Slava Chernyak, Reuven Lax", 35, user2));
        listOfBooks.add(new Book("Deep Learning Cookbook", "Douwe Osinga", 35, user3));
        listOfBooks.add(new Book("Jenkins 2: Up and Running", "Brent Laster", 35, user4));
        Library library = new Library(" 50 place republic ", listOfBooks);
        return gson.toJson(library);
    }

    public Library mapListDeserialization(InputStream inputStream) throws UnsupportedEncodingException {
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        return gson.fromJson(reader, Library.class);
    }
}
