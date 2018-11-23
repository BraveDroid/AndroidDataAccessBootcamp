package com.bravedroid.dataaccess.model;

public class UserDayEnum {

    private String _name;
    private String email;
    private boolean isDeveloper;
    private int age;
    private Day day;

    public UserDayEnum(String _name, String email, boolean isDeveloper, int age, Day day) {
        this._name = _name;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.age = age;
        this.day = day;
    }

    public enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UserDayEnum
                && this._name.equals(((UserDayEnum) other)._name)
                && this.email.equals(((UserDayEnum) other).email)
                && this.isDeveloper == ((UserDayEnum) other).isDeveloper
                && this.age == ((UserDayEnum) other).age
                && this.day == ((UserDayEnum) other).day;
    }
}
