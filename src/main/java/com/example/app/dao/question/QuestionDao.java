package com.example.app.dao.question;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.question.Question;
import com.example.app.model.question.QuestionType;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private static final Logger LOGGER = Logger.getLogger(QuestionDao.class);

    private static QuestionDao INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
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

    public Question createQuestion(Question question) {
        LOGGER.info("Creating new question");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, question.getQuizId());
            statement.setString(2, question.getText());
            statement.setInt(3, question.getType().ordinal());
            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Question creation failed");
            }
            else {
                LOGGER.info("Question creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        question.setId(generatedKeys.getInt(1));
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

    public Question updateQuestion(Question question) {
        LOGGER.info("Updating question");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setLong(1, question.getQuizId());
            statement.setString(2, question.getText());
            statement.setInt(3, question.getType().ordinal());
            statement.setInt(4, question.getId());

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

    public boolean deleteQuestionById(int id) {
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
                question = getQuestion(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return question;
    }

    public List<Question> findAll(Integer quizId, Integer offset, Integer size) {
        LOGGER.info("QuestionDao getting page with offset " + offset + ", size " + size + " for quizId " + quizId);
        List<Question> res = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAllQuery);
            statement.setInt(1, quizId);
            statement.setInt(2, offset);
            statement.setInt(3, size);

            res = getQuestions(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    private List<Question> getQuestions(ResultSet resultSet) {
        List<Question> res = new ArrayList<>();
        try {
            while (resultSet.next()) { res.add(getQuestion(resultSet)); }
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }

        return res;
    }

    private Question getQuestion(ResultSet resultSet) throws SQLException {
        Question question = new Question(
                resultSet.getInt("id"),
                resultSet.getInt("quizId"),
                resultSet.getString("text"),
                QuestionType.values()[resultSet.getInt("type")],
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("updatedAt")
                );
        return question;
    }

}
