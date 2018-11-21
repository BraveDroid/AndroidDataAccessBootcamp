package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserVersionSerializer {
    public Gson gson;

    UserVersionSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setVersion(1);
        gson = gsonBuilder.create();
    }

    public String serializeUserVersion(UserVersion userVersion) {
        return gson.toJson(userVersion);
    }
}
