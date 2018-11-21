package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.HtmlContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HtmlContentParser {
    private Gson gson;

    public String parseHtmlContent(HtmlContent htmlContent) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.disableHtmlEscaping();
        gson = gsonBuilder.create();
        return gson.toJson(htmlContent);
    }
}
