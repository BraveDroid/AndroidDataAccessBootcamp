package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserFloat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ValuesOfFloatsSerializer {
    private Gson gson;

    ValuesOfFloatsSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        gson = gsonBuilder.create();
    }

    public String serializeValuesOfFloats() {
        UserFloat userFloat = new UserFloat("Norman", Float.POSITIVE_INFINITY);
        return gson.toJson(userFloat);
    }
}
