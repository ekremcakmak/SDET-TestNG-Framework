package com.TestNG.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    static Properties config;
    static {
        String path= "config.properties";
        FileInputStream file = null;

        try {
            file = new FileInputStream(path);
            config = new Properties();
            config.load(file);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public static String getConfig (String key)
    {
        return config.getProperty(key);
    }
}
