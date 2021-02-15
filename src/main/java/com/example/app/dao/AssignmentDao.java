package com.example.app.dao;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.assignment.Assignment;
import com.example.app.model.assignment.AssignmentStatus;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDao {
    private static final Logger LOGGER = Logger.getLogger(AssignmentDao.class);
    private static AssignmentDao INSTANCE;
    private static ConnectionPool connectionPool;
    private static String createQuery;
    private static String updateQuery;
    private static String findByUserIdQuery;
    private static String findByUserIdQuizIdQuery;
    private static String findAllQuery;

    private AssignmentDao() {
        LOGGER.info("Initializing AssignmentDao");
        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();
        createQuery = properties.getProperty("createUserQuiz");
        updateQuery = properties.getProperty("updateUserQuizByUserIdQuizId");
        findByUserIdQuery = properties.getProperty("findUserQuizByUserId");
        findByUserIdQuizIdQuery = properties.getProperty("findUserQuizByUserIdQuizId");
        findAllQuery = properties.getProperty("findAllUserQuiz");
    }

    public static AssignmentDao getInstance() {
        if(INSTANCE == null) { INSTANCE = new AssignmentDao(); }
        return INSTANCE;
    }

    public Assignment createUserQuiz(Assignment assignment) {
        LOGGER.info("Creating new assignment");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, assignment.getUserId());
            statement.setInt(2, assignment.getQuizId());
            LOGGER.info("Assignment creation " + (statement.executeUpdate() == 0 ? "failed" : "successful"));
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return assignment;
    }

    public Assignment updateUserQuiz(Assignment assignment) {
        LOGGER.info("Updating assignment");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, assignment.getScore());
            statement.setInt(2, assignment.getStatus().ordinal());
            statement.setInt(3, assignment.getUserId());
            statement.setInt(4, assignment.getQuizId());
            LOGGER.info(statement.execute() ? "Assignment update failed" : "Assignment updated successfully");
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return assignment;
    }

    public Assignment findUserQuizByUserId(int userId) {
        LOGGER.info("Getting assignment with userId " + userId);
        Assignment assignment = null;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(findByUserIdQuery)) {
            statement.setInt(1, userId);
            try(ResultSet result = statement.executeQuery()){
                assignment = result.next() ? getUserQuiz(result, false) : null;
            }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return assignment;
    }

    public Assignment findUserQuizByUserIdQuizId(int userId, int quizId, boolean getQuestions) {
        LOGGER.info("Getting Assignment by userId: " + userId + " quizId: " + quizId);
        Assignment assignment = null;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(findByUserIdQuizIdQuery)) {
            statement.setInt(1, userId);
            statement.setInt(2, quizId);
            try(ResultSet result = statement.executeQuery()){
                assignment = result.next() ? getUserQuiz(result, getQuestions) : null;
            }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return assignment;
    }

    public List<Assignment> findAll(Integer userId, Integer offset, Integer size) {
        LOGGER.info("Assignment getting page with offset " + offset + ", size " + size + " for userId " + userId);
        List<Assignment> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAllQuery);
            statement.setInt(1, userId);
            statement.setInt(2, offset);
            statement.setInt(3, size);
            try(ResultSet resultSet = statement.executeQuery()){ res = getUserQuizzes(resultSet); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private List<Assignment> getUserQuizzes(ResultSet resultSet) {
        List<Assignment> res = new ArrayList<>();
        try {
            while (resultSet.next()) { res.add(getUserQuiz(resultSet, true)); }
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private Assignment getUserQuiz(ResultSet resultSet, boolean getQuestions) throws SQLException {
        Assignment assignment = new Assignment(
                resultSet.getInt("userId"),
                resultSet.getInt("quizId"),
                resultSet.getInt("score"),
                AssignmentStatus.values()[resultSet.getInt("status")],
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("updatedAt"),
                resultSet.getTimestamp("startedAt"),
                resultSet.getTimestamp("finishedAt")
        );
        if (getQuestions) assignment.setQuiz(new QuizDao().findQuizById(assignment.getQuizId()));
        return assignment;
    }
}
