package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.User;
import com.google.gson.Gson;

public class SimpleParsing {
    private final Gson gson;

    public SimpleParsing() {
        gson = new Gson();
    }

    public String toJsonString(User user) {
        return gson.toJson(user);
    }

    public User toObject(String userString) {
        return gson.fromJson(userString, User.class);
    }
}
