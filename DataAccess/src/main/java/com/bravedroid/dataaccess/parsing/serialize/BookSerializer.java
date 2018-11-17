package com.bravedroid.dataaccess.parsing.serialize;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.bravedroid.dataaccess.model.Book;

import java.io.*;
import java.util.Base64;

public class BookSerializer {

    @TargetApi(Build.VERSION_CODES.O)
    public String serializeBookToString(Book book) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(book);
        objectOutputStream.flush();
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Book deserializeBookFromString(String bookString) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(bookString);
        InputStream in = new ByteArrayInputStream(data);
        ObjectInputStream obin = new ObjectInputStream(in);
        return (Book) obin.readObject();
    }

    public void serializeBookToDisk(Book book, String fileName) throws IOException {
        FileOutputStream fileStream = new FileOutputStream(fileName);
        ObjectOutputStream os = new ObjectOutputStream(fileStream);
        os.writeObject(book);
        os.close();
    }

    public Book deserializeBookFromDisk(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInStream = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fileInStream);
        Book book = (Book) ois.readObject();
        ois.close();
        return book;
    }
}
