package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserDayEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class EnumsMapping {
    private Gson gson;

    public EnumsMapping() {
        gson = new Gson();
    }

    public String serializeEnums(UserDayEnum userDayEnum) {
        return gson.toJson(userDayEnum);
    }

    public UserDayEnum deserializeEnums(String input) {
        Type userDayEnum = new TypeToken<UserDayEnum>() {
        }.getType();
        return gson.fromJson(input, userDayEnum);
    }
}
