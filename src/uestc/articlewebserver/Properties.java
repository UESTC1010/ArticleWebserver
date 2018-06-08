/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uestc.articlewebserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 程序参数
 *
 * @author uestc 1010
 */
public class Properties {

    private FileWriter writer;
    private java.util.Properties appProps;

    private String propertiesFileStr = "ArticleWebserver.properties";

    private static Properties properties = new Properties();

    public static Properties getProperties() {
        return properties;
    }

    private Properties() {
        appProps = new java.util.Properties();

        try {

            File propertiesFile = new File(propertiesFileStr);
            InputStream is = null;
            if (propertiesFile.exists()) {
                is = new FileInputStream(propertiesFile);
            } else {
                is = Properties.class.getClassLoader().getResourceAsStream("resources/ArticleWebserver.properties");
            }
            appProps.load(is);
            if (!propertiesFile.exists()) {
                saveProperties();
            }

        } catch (Exception e) {
            System.out.println("File app.properties missing.");
        }

    }

    public String getProperty(String key) {
        return appProps.getProperty(key);
    }

    public void saveProperty(String key, String value) {
        appProps.setProperty(key, value);
    }

    public void saveProperties() {
        try {
            if (writer == null) {
                writer = new FileWriter(new File(propertiesFileStr));
            }
            appProps.store(writer, "store to properties file");
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) throws IOException {
        for (String n : Properties.getProperties().appProps.stringPropertyNames()) {
            System.out.println(n + " " + Properties.getProperties().appProps.getProperty(n));
        }
    }
}
