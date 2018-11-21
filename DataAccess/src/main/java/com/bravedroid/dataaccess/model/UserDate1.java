package com.bravedroid.dataaccess.model;

import java.util.Date;

public class UserDate1 {

    public String _name;
    public String email;
    public boolean isDeveloper;
    public int age;
    public Date registerDate;

    public UserDate1(String _name, String email, boolean isDeveloper, int age, Date registerDate) {
        this._name = _name;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.age = age;
        this.registerDate = registerDate;
    }
}
