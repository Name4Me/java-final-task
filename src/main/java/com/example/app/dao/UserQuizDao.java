package com.example.app.dao;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.model.userQuize.UserQuizStatus;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserQuizDao {
    private static final Logger LOGGER = Logger.getLogger(UserQuizDao.class);

    private static UserQuizDao INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String findByUserIdQuery;
    private static String findByUserIdQuizIdQuery;
    private static String findAllQuery;

    private UserQuizDao() {
        LOGGER.info("Initializing UserQuizDao");

        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createUserQuiz");
        updateQuery = properties.getProperty("updateUserQuizByUserIdQuizId");
        findByUserIdQuery = properties.getProperty("findUserQuizByUserId");
        findByUserIdQuizIdQuery = properties.getProperty("findUserQuizByUserIdQuizId");
        findAllQuery = properties.getProperty("findAllUserQuiz");
    }

    public static UserQuizDao getInstance() {
        if(INSTANCE == null) { INSTANCE = new UserQuizDao(); }
        return INSTANCE;
    }

    public UserQuiz createUserQuiz(UserQuiz userQuiz) {
        LOGGER.info("Creating new userQuiz");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userQuiz.getUserId());
            statement.setInt(2, userQuiz.getQuizId());
            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("UserQuiz creation failed");
            } else { LOGGER.info("UserQuiz creation successful"); }
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }

        return userQuiz;
    }

    public UserQuiz updateUserQuiz(UserQuiz userQuiz) {
        LOGGER.info("Updating userQuiz");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, userQuiz.getScore());
            statement.setInt(2, userQuiz.getStatus().ordinal());
            statement.setInt(3, userQuiz.getUserId());
            statement.setInt(4, userQuiz.getQuizId());
            LOGGER.info(statement.execute() ? "UserQuiz update failed" : "UserQuiz updated successfully");
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }

        return userQuiz;
    }

    public UserQuiz findUserQuizByUserId(int userId) {
        LOGGER.info("Getting userQuiz with userId " + userId);
        UserQuiz userQuiz = null;

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByUserIdQuery);
            statement.setLong(1, userId);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                userQuiz = getUserQuiz(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return userQuiz;
    }

    public UserQuiz findUserQuizByUserIdQuizId(int userId, int quizId) {
        LOGGER.info("Getting UserQuiz by userId: " + userId + " quizId: " + quizId);
        UserQuiz userQuiz = null;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByUserIdQuizIdQuery);
            statement.setInt(1, userId);
            statement.setInt(2, quizId);
            ResultSet result = statement.executeQuery();
            userQuiz = result.next() ? getUserQuiz(result) : null;
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }

        return userQuiz;
    }

    public List<UserQuiz> findAll(Integer userId, Integer offset, Integer size) {
        LOGGER.info("UserQuiz getting page with offset " + offset + ", size " + size + " for userId " + userId);
        List<UserQuiz> res = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAllQuery);
            statement.setInt(1, userId);
            statement.setInt(2, offset);
            statement.setInt(3, size);

            res = getUserQuizzes(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    private List<UserQuiz> getUserQuizzes(ResultSet resultSet) {
        List<UserQuiz> res = new ArrayList<>();
        try {
            while (resultSet.next()) { res.add(getUserQuiz(resultSet)); }
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }

        return res;
    }

    private UserQuiz getUserQuiz(ResultSet resultSet) throws SQLException {
        UserQuiz userQuiz = new UserQuiz(
                resultSet.getInt("userId"),
                resultSet.getInt("quizId"),
                resultSet.getInt("score"),
                UserQuizStatus.values()[resultSet.getInt("status")],
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("updatedAt"),
                resultSet.getTimestamp("finishedAt")
        );
        userQuiz.setQuiz(new QuizDao().findQuizById((long) userQuiz.getQuizId()));
        return userQuiz;
    }
}
