package com.purgomalum.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProps {

    static Properties prop;
    static FileInputStream fileInputStream;
    public static String name = "/src/main/resources/config.properties";

    private static File currentDirectory = new File(new File("").getAbsolutePath());

    public static String getproperty(String key) {
        prop = new Properties();

        try {
            fileInputStream = new FileInputStream(currentDirectory + name);
            prop.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
