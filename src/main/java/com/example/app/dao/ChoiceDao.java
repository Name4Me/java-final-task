package com.example.app.dao;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.Choice;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoiceDao {
    private static final Logger LOGGER = Logger.getLogger(ChoiceDao.class);

    private static ChoiceDao INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findAllQuery;
    private static String findAllQueryWithLimits;

    private ChoiceDao() {
        LOGGER.info("Initializing ChoiceDao");
        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();
        createQuery = properties.getProperty("createChoice");
        updateQuery = properties.getProperty("updateChoiceById");
        deleteQuery = properties.getProperty("deleteChoiceById");
        findAllQuery = properties.getProperty("findAllChoices");
        findAllQueryWithLimits = properties.getProperty("findAllChoicesWithLimits");
    }

    public static ChoiceDao getInstance() {
        if(INSTANCE == null) { INSTANCE = new ChoiceDao(); }
        return INSTANCE;
    }

    public Choice createChoice(Choice choice) {
        LOGGER.info("Creating new choice");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, choice.getQuestionId());
            statement.setString(2, choice.getText());
            statement.setBoolean(3, choice.getIsCorrect());
            if (statement.executeUpdate() != 0) {
                LOGGER.info("Choice creation successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        choice.setId(generatedKeys.getInt(1));
                    } else { LOGGER.error("Failed to create choice, no ID obtained."); }
                }
            } else { LOGGER.info("Choice creation failed"); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return choice;
    }

    public Choice updateChoice(Choice choice) {
        LOGGER.info("Updating choice");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, choice.getQuestionId());
            statement.setString(2, choice.getText());
            statement.setBoolean(3, choice.getIsCorrect());
            statement.setInt(4, choice.getId());
            if(statement.execute()) {
                LOGGER.error("Choice update failed");
            } else { LOGGER.info("Choice updated successfully"); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return choice;
    }

    public boolean deleteChoiceById(int id) {
        LOGGER.info("Deleting choice");
        boolean result = false;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            result = !statement.execute();
            if (result) {
                LOGGER.info("Choice deleted successfully");
            } else { LOGGER.info("Choice deletion failed"); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return result;
    }
    public List<Choice> findAll(Integer questionId) {
        return findAll(questionId, 0, 0);
    }
    public List<Choice> findAll(Integer questionId, Integer offset, Integer size) {
        LOGGER.info("ChoiceDao getting page with offset " + offset + ", size " + size + " for questionId " + questionId);
        List<Choice> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(size == 0 ? findAllQuery : findAllQueryWithLimits)) {
            statement.setInt(1, questionId);
            if(size != 0){
                statement.setInt(2, offset);
                statement.setInt(3, size);
            }
            try(ResultSet resultSet = statement.executeQuery()){
                res = getChoices(resultSet);
            }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private List<Choice> getChoices(ResultSet resultSet) {
        List<Choice> res = new ArrayList<>();
        try {
            while (resultSet.next()) { res.add(getChoice(resultSet)); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private Choice getChoice(ResultSet resultSet) throws Exception {
        return new Choice(
                resultSet.getInt("id"),
                resultSet.getInt("questionId"),
                resultSet.getString("text"),
                resultSet.getBoolean("isCorrect"),
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("updatedAt")
        );
    }
}
