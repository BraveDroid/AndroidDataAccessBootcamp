package com.bravedroid.dataaccess.parsing.properties;

import com.bravedroid.dataaccess.model.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.bravedroid.dataaccess.model.Config.KEY_BASE_URL;
import static com.bravedroid.dataaccess.model.Config.KEY_IS_PROD;
import static com.bravedroid.dataaccess.model.Config.KEY_PORT;

public class SampleConfigPropertiesMapping {

    public Config getConfig(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Config config = new Config();
        config.setBaseUrl(properties.getProperty(KEY_BASE_URL));
        config.setPort(Integer.parseInt(properties.getProperty(KEY_PORT)));
        config.setProd(Boolean.parseBoolean(properties.getProperty(KEY_IS_PROD)));
        return config;
    }
}
