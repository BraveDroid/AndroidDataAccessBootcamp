package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.SimpleUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ForceSerializationOfNullValues {
    Gson gson;

    public ForceSerializationOfNullValues() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gson = gsonBuilder.create();
    }

    public String serializeNullValues() {
        SimpleUser simpleUser = new SimpleUser("Norman", null, true, 26);
        return gson.toJson(simpleUser);
    }

    public SimpleUser deserializeNullValues(String input) {
        Type simpleUser = new TypeToken<SimpleUser>() {
        }.getType();
        return gson.fromJson(input, simpleUser);
    }
}
