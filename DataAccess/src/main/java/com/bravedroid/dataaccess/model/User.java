package com.bravedroid.dataaccess.model;

import java.io.Serializable;

public class User implements Serializable {
    private int age;
    private String firstName;
    private String lastName;

    public User(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof User
                && this.age == ((User) (other)).age
                && this.firstName.equals(((User) other).firstName)
                && this.lastName.equals(((User) other).lastName);
    }
}
