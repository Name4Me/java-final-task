package com.example.app.util;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
class TestHelperTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    //private static final String URL_CONNECTION = "jdbc:h2:~/test;user=test;password=test";

    @BeforeClass
    public static void beforeTest() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

/*        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        dbManager = DBManager.getInstance();

        try (Connection con = DriverManager.getConnection(URL_CONNECTION);
             Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, login VARCHAR(10) UNIQUE);";
            statement.executeUpdate(sql);
        }*/


    }

    @Test
    void getInstance() {
        TestHelper th = TestHelper.getInstance();
        assertNotNull(th);
        th.createUsers();
        th.insertUsers();
    }

    @Test
    void createUsers() {
    }

    @Test
    void insertUsers() {
    }
}