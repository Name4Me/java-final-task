package com.example.app.dao;

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
    private static String findAllQuery;

    private QuestionDao() {
        LOGGER.info("Initializing Question");
        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();
        createQuery = properties.getProperty("createQuestion");
        updateQuery = properties.getProperty("updateQuestionById");
        deleteQuery = properties.getProperty("deleteQuestionById");
        findAllQuery = properties.getProperty("findAllQuestions");
    }

    public static QuestionDao getInstance() {
        if(INSTANCE == null) { INSTANCE = new QuestionDao(); }
        return INSTANCE;
    }

    public Question createQuestion(Question question) {
        LOGGER.info("Creating new question");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, question.getQuizId());
            statement.setString(2, question.getText());
            statement.setInt(3, question.getType().ordinal());
            if (statement.executeUpdate() != 0) {
                LOGGER.info("Question creation successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        question.setId(generatedKeys.getInt(1));
                    } else { LOGGER.error("Failed to create question, no ID obtained."); }
                }
            } else { LOGGER.info("Question creation failed"); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return question;
    }

    public Question updateQuestion(Question question) {
        LOGGER.info("Updating question");
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, question.getQuizId());
            statement.setString(2, question.getText());
            statement.setInt(3, question.getType().ordinal());
            statement.setInt(4, question.getId());
            LOGGER.info("Question update "+ (statement.execute() ? "failed" : "successfully"));
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return question;
    }

    public boolean deleteQuestionById(int id) {
        LOGGER.info("Deleting question");
        boolean result = false;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            result = !statement.execute();
            LOGGER.info("Question deletion "+(result ? "successfully" : "failed"));
        } catch (SQLException e) { LOGGER.error(e.getMessage()); }
        return result;
    }

    public List<Question> findAll(Integer quizId, Integer offset, Integer size) {
        LOGGER.info("QuestionDao getting page with offset " + offset + ", size " + size + " for quizId " + quizId);
        List<Question> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(findAllQuery)) {
            statement.setInt(1, quizId);
            statement.setInt(2, offset);
            statement.setInt(3, size);
            try(ResultSet resultSet = statement.executeQuery()){ res = getQuestions(resultSet); }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private List<Question> getQuestions(ResultSet resultSet) {
        List<Question> res = new ArrayList<>();
        try {
            while (resultSet.next()) res.add(getQuestion(resultSet));
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private Question getQuestion(ResultSet resultSet) throws Exception {
        Question question = new Question(
                resultSet.getInt("id"),
                resultSet.getInt("quizId"),
                resultSet.getString("text"),
                QuestionType.values()[resultSet.getInt("type")],
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("updatedAt")
                );
        question.setChoices(ChoiceDao.getInstance().findAll(question.getId()));
        return question;
    }

}
