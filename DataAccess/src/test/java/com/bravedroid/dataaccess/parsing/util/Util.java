package com.bravedroid.dataaccess.parsing.util;

import java.io.File;
import java.net.URL;

public class Util {
    public static File getFileFromResourcePath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }
}
