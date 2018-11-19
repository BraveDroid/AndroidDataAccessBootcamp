package com.bravedroid.dataaccess.model;

public class SimpleUser {
    public String name;
    public String email;
    public boolean isDeveloper;
    public int age;

    public SimpleUser(String name, String email, boolean isDeveloper, int age) {
        this.age = age;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.name = name;
    }
}
