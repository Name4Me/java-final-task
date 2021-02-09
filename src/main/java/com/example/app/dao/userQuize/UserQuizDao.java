package com.example.app.dao.userQuize;

import com.example.app.connection.ConnectionPool;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

public class UserQuizDao {
    private static final Logger LOGGER = Logger.getLogger(UserQuizDao.class);

    private static UserQuizDao INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String findByNameQuery;
    private static String findAllQuery;

    private UserQuizDao() {
        LOGGER.info("Initializing UserQuizDao");

        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createUserQuiz");
        updateQuery = properties.getProperty("updateUserQuizById");
        deleteQuery = properties.getProperty("deleteUserQuizById");
        findByIdQuery = properties.getProperty("findUserQuizById");
        findAllQuery = properties.getProperty("findAllUserQuiz");
    }

    public static UserQuizDao getInstance() {
        if(INSTANCE == null) { INSTANCE = new UserQuizDao(); }
        return INSTANCE;
    }
}
