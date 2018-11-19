package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserDate;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;

public class ParsingWithExclusionStrategies {
    private Gson gson;

    public ParsingWithExclusionStrategies() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.FINAL);
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("_");
            }

            @Override
            public boolean shouldSkipClass(Class<?> incomingClass) {
                return incomingClass == Date.class || incomingClass == boolean.class;
            }
        });
        gson = gsonBuilder.create();
    }

    public String serializeWithExclusionStrategies(UserDate userDate) {
        new UserDate("Norman", "norman@futurestud.io", true, 26, new Date());
        return gson.toJson(userDate);
    }

    public UserDate deserializeWithExclusionStrategies(String input) {
        Type userDate = new TypeToken<UserDate>() {
        }.getType();
        return gson.fromJson(input, userDate);
    }
}
