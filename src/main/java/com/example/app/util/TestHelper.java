package com.example.app.util;

import com.example.app.connection.ConnectionPool;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestHelper {
    private static final Logger LOGGER = Logger.getLogger(TestHelper.class);

    private static TestHelper INSTANCE;
    private static ConnectionPool connectionPool;
    private static final String createUsers = "CREATE TABLE IF NOT EXISTS  users (\n" +
            "                           id int NOT NULL AUTO_INCREMENT,\n" +
            "                           password blob NOT NULL,\n" +
            "                           email varchar(50) NOT NULL,\n" +
            "                           userType int NOT NULL DEFAULT 1,\n" +
            "                           status int NOT NULL DEFAULT 1,\n" +
            "                           PRIMARY KEY (id)\n" +
            ")";

    /**
     * @language SQL
     */
    private static final String insertUsers = "INSERT INTO users (email, password, userType) values ('admin@gmail.com',?,1)";

    private TestHelper() {
        LOGGER.info("Initializing MysqlUserDaoImpl");
        connectionPool = ConnectionPool.getInstance();
    }

    public static TestHelper getInstance() {
        if(INSTANCE == null) { INSTANCE = new TestHelper(); }
        return INSTANCE;
    }

    public void createUsers(){
        LOGGER.info("createUsers");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(createUsers)) {
            LOGGER.info("createUsers " + (statement.execute() ? "failed" : "successfully"));
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
    }

    public void insertUsers(){
        LOGGER.info("insertUsers");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertUsers)) {
            statement.setBytes(1, Password.getPasswordHash("1"));
            LOGGER.info("insertUsers " + (statement.execute() ? "failed" : "successfully"));
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
    }

    private boolean execute(Connection connection, String sql){
        boolean result = false;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            result = statement.execute() ? false : true;
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return result;
    }

}
