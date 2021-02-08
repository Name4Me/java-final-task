package com.example.app.dao.question;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.question.Question;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;

public class QuestionDao {
    private static final Logger LOGGER = Logger.getLogger(QuestionDao.class);

    private static QuestionDao INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String findByNameQuery;
    private static String findAllQuery;

    private QuestionDao() {
        LOGGER.info("Initializing Question");

        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createQuestion");
        updateQuery = properties.getProperty("updateQuestionById");
        deleteQuery = properties.getProperty("deleteQuestionById");
        findByIdQuery = properties.getProperty("findQuestionById");
        findAllQuery = properties.getProperty("findAllQuestions");
    }

    public static QuestionDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new QuestionDao();
        }
        return INSTANCE;
    }

    public Question createQuiz(Question question) {
        LOGGER.info("Creating new question");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(2, question.getQuizId());
            statement.setString(1, question.getText());
            statement.setInt(3, question.getType().ordinal());
            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Question creation failed");
            }
            else {
                LOGGER.info("Question creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        question.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create question, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return question;
    }

    public Question updateQuiz(Question question) {
        LOGGER.info("Updating question");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setLong(2, question.getQuizId());
            statement.setString(1, question.getText());
            statement.setInt(3, question.getType().ordinal());
            statement.setLong(4, question.getId());

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Question update failed");
            }
            else {
                LOGGER.info("Question updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return question;
    }

    public boolean deleteQuizById(Long id) {
        LOGGER.info("Deleting question");
        boolean res = false;

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Question deletion failed");
            }
            else {
                LOGGER.info("Question deleted successfully");
                res = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    public Question findQuestionById(Long id) {
        LOGGER.info("Getting question with id " + id);
        Question question = null;

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                question = new Question();
                question.setId(result.getLong("id"));
                question.setText(result.getString("text"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return question;
    }
}
