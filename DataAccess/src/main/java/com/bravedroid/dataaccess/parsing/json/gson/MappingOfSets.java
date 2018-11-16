package com.bravedroid.dataaccess.parsing.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class MappingOfSets {
    private Gson gson;

    public MappingOfSets() {
        this.gson = new Gson();
    }

    public String serialiseHashSet() {
        HashSet<String> users = new HashSet<>();
        users.add("Christian");
        users.add("Marcus");
        users.add("Norman");
        users.add("Marcus");

        return gson.toJson(users);
    }

    public Set<String> deserializeHashSet(String hashSetUser) {
        Type employeesSetType = new TypeToken<HashSet<String>>() {
        }.getType();
        return gson.fromJson(hashSetUser, employeesSetType);
    }
}
