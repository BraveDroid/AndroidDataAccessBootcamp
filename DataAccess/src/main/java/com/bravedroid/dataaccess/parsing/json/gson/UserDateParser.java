package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserDate1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDateParser {
    private Gson gson;

    public UserDateParser() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //*****gsonBuilder.setDateFormat(DateFormat.FULL, DateFormat.FULL);//"Wednesday, November 21, 2018 10:26:01 PM CET\"
        //gsonBuilder.setDateFormat(DateFormat.SHORT, DateFormat.SHORT);//"11/21/18 10:25 PM\"***//
        gsonBuilder.setDateFormat("h:mm a");//"12:08 PM\"***//
        gson = gsonBuilder.create();
    }

    public String serializeUserDate(UserDate1 userDate){
        return gson.toJson(userDate);
    }
}
