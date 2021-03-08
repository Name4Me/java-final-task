package com.example.app.properties;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MysqlQueryProperties {
    private static final Logger LOGGER = Logger.getLogger(MysqlQueryProperties.class);
    private static MysqlQueryProperties instance = null;
    private final Properties properties;
    private static final String propertiesFileName = "mysqlQueries.properties";

    private MysqlQueryProperties() {
        LOGGER.info("Initializing MysqlQueryProperties class");
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                LOGGER.error("Mysql queries property file not found on the classpath");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static synchronized MysqlQueryProperties getInstance() {
        if (instance == null) {
            instance = new MysqlQueryProperties();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
