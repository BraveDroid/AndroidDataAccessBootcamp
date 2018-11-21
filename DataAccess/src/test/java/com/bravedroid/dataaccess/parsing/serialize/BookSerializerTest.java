package com.bravedroid.dataaccess.parsing.serialize;

import com.bravedroid.dataaccess.model.Book;
import com.bravedroid.dataaccess.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookSerializerTest {
    private BookSerializer SUT;

    @Before
    public void setUp() {
        SUT = new BookSerializer();
    }

    @Test
    public void serializeBookToStringTest() throws IOException {
        String result = SUT.serializeBookToString(new Book("OCP", "ORACLE", 40f, new User(40, "Rondy", "Rozy")));
        String expected = "rO0ABXNyACRjb20uYnJhdmVkcm9pZC5kYXRhYWNjZXNzLm1vZGVsLkJvb2tKUkYCOsKmlQIABEYABXByaWNlTAAGYXV0aG9ydAASTGphdmEvbGFuZy9TdHJpbmc7TAAFdGl0bGVxAH4AAUwABHVzZXJ0ACZMY29tL2JyYXZlZHJvaWQvZGF0YWFjY2Vzcy9tb2RlbC9Vc2VyO3hwQiAAAHQABk9SQUNMRXQAA09DUHNyACRjb20uYnJhdmVkcm9pZC5kYXRhYWNjZXNzLm1vZGVsLlVzZXJuaz8cqxNeEAIAA0kAA2FnZUwACWZpcnN0TmFtZXEAfgABTAAIbGFzdE5hbWVxAH4AAXhwAAAAKHQABVJvbmR5dAAEUm96eQ==";
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeBookTest() throws IOException, ClassNotFoundException {
        String input = "rO0ABXNyACRjb20uYnJhdmVkcm9pZC5kYXRhYWNjZXNzLm1vZGVsLkJvb2tKUkYCOsKmlQIABEYABXByaWNlTAAGYXV0aG9ydAASTGphdmEvbGFuZy9TdHJpbmc7TAAFdGl0bGVxAH4AAUwABHVzZXJ0ACZMY29tL2JyYXZlZHJvaWQvZGF0YWFjY2Vzcy9tb2RlbC9Vc2VyO3hwQiAAAHQABk9SQUNMRXQAA09DUHNyACRjb20uYnJhdmVkcm9pZC5kYXRhYWNjZXNzLm1vZGVsLlVzZXJuaz8cqxNeEAIAA0kAA2FnZUwACWZpcnN0TmFtZXEAfgABTAAIbGFzdE5hbWVxAH4AAXhwAAAAKHQABVJvbmR5dAAEUm96eQ==";
        Book book = SUT.deserializeBookFromString(input);
        assertThat(book, is(equalTo(new Book("OCP", "ORACLE", 40f, new User(40, "Rondy", "Rozy")))));
    }

    @Test
    public void serializeBookToDiskTest() throws IOException {
        Book book1 = new Book("OCP", "ORACLE", 40f, new User(40, "Rondy", "Rozy"));
        String fileName = "book.txt";
        SUT.serializeBookToDisk(book1, fileName);
        File file = new File(fileName);
        assertThat(file.exists(), is(true));
    }

    @Test
    public void deserializeBookFromDiskTest() throws IOException, ClassNotFoundException {
        Book expected = new Book("OCP", "ORACLE", 40f, new User(40, "Rondy", "Rozy"));
        String fileName = "book.txt";
        Book book = SUT.deserializeBookFromDisk(fileName);
        File file = new File(fileName);
        if (!file.exists()) SUT.serializeBookToDisk(expected, fileName);
        assertThat(book, is(equalTo(expected)));
    }
}
