package com.bravedroid.dataaccess.parsing.base64;

import android.util.Base64;

public class Base64Helper {

    public String enCodeToBase64(String string) {
        return Base64.encodeToString(string.getBytes(), Base64.NO_WRAP | Base64.URL_SAFE);
    }

    public String decodeFromBase64(String encodedString) {
        return new String(Base64.decode(encodedString, Base64.NO_WRAP | Base64.URL_SAFE));
    }
}
