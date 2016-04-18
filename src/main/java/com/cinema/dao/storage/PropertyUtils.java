package com.cinema.dao.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtils {
    private static String propFile = "property.properties";
    private static Properties properties = null;

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFile);
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}

