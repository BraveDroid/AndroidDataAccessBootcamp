package com.bravedroid.dataaccess.parsing.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MappingOfMaps {
    public String serializeMap() {
        HashMap<String, List<String>> employees = new HashMap<>();
        employees.put("A", Arrays.asList("Andreas", "Arnold", "Aden"));
        employees.put("C", Arrays.asList("Christian", "Carter"));
        employees.put("M", Arrays.asList("Marcus", "Mary"));
        Gson gson = new Gson();
        return gson.toJson(employees);
    }

    public HashMap<String, List<String>> deserializeMap(String employeesJsonString) {
        Gson gson = new Gson();
        Type employeesMapType = new TypeToken<HashMap<String, List<String>>>() {
        }.getType();
        return gson.fromJson(employeesJsonString, employeesMapType);
    }
}
