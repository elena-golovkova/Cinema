package com.cinema.dao.storage;

import java.io.*;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class Configuration {
    private static Configuration configuration;
    public static final String DB = "db.isMySQL";
    public static final String DRIVER_CLASS = "db.driverClass";
    public static final String LOGIN = "db.login";
    public static final String PASSWORD = "db.password";
    public static final String JDBC_URL = "db.JdbcUrl";
    private Properties prop;

    private Configuration() {
        prop = new Properties();
        final String path = "E:\\java\\Cinema\\src\\resources\\property.properties";
        try (InputStream input = new FileInputStream(new File(String.valueOf(path)))) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        if (configuration == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    configuration = new Configuration();
                }
            }
        }
        return configuration;
    }


    public synchronized String getProperty(String key) {
        String value = null;

        if (prop.containsKey(key))
            value = (String) prop.get(key);
        else {
            throw new NoSuchElementException("There is no such property");
        }
        return value;
    }


}
