package com.bravedroid.dataaccess.model;

import java.util.Date;

public class UserDate {
    public String _name;
    public transient String email;
    public static boolean isDeveloper;
    public final int age;
    public Date registerDate;

    public UserDate(String _name, String email, boolean isDeveloper, int age, Date registerDate) {
        this._name = _name;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.age = age;
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserDate
                && this._name.equals(((UserDate) obj)._name)
                && this.email.equals(((UserDate) obj).email)
                && this.age == ((UserDate) obj).age
                && this.registerDate.equals(((UserDate) obj).registerDate);
    }
}
