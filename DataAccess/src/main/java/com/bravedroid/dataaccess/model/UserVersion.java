package com.bravedroid.dataaccess.model;

import com.google.gson.annotations.Since;

public class UserVersion {
    private String name;

    @Since(1)
    private String email;

    @Since(2)
    private boolean isDeveloper;

    @Since(2.1)
    private int age;

    public UserVersion(String name, String email, boolean isDeveloper, int age) {
        this.age = age;
        this.email = email;
        this.name = name;
        this.isDeveloper = isDeveloper;
    }
}
