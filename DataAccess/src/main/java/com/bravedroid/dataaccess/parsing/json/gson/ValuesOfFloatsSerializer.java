package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserFloat;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ValuesOfFloatsSerializer {

    public String serializeValuesOfFloats(UserFloat userFloat) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        Gson gson = gsonBuilder.create();
        return gson.toJson(userFloat);
    }

    public UserFloat deserializeValuesOfFloats(String userStringJson) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Float.class, new FloatDeserializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(userStringJson, UserFloat.class);
    }

    private static class FloatDeserializer implements JsonDeserializer<Float> {
        public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            String data = json.getAsJsonPrimitive().getAsString();
            return Float.parseFloat(data);
        }
    }
}
