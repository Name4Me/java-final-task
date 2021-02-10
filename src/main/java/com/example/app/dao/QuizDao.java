package com.example.app.dao;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.quiz.QuizDifficulty;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDao {

    private static final Logger LOGGER = Logger.getLogger(QuizDao.class);

    private static QuizDao INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String findByNameQuery;
    private static String findAllQuery;

    public QuizDao() {
        LOGGER.info("Initializing MysqlQuizDaoImpl");

        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createQuiz");
        updateQuery = properties.getProperty("updateQuizById");
        deleteQuery = properties.getProperty("deleteQuizById");
        findByIdQuery = properties.getProperty("findQuizById");
        findByNameQuery = properties.getProperty("findQuizByName");
        findAllQuery = properties.getProperty("findAllQuizzes");
    }

    public static QuizDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new QuizDao();
        }
        return INSTANCE;
    }

    public Quiz createQuiz(Quiz quiz) {
        LOGGER.info("Creating new quiz");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, quiz.getName());
            statement.setString(2, quiz.getDescription());
            statement.setInt(3, quiz.getDifficulty().ordinal());
            statement.setInt(4, quiz.getTime());
            statement.setInt(5, quiz.getNumberOfQuestion());
            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Quiz creation failed");
            }
            else {
                LOGGER.info("Quiz creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        quiz.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create quiz, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return quiz;
    }

    public Quiz updateQuiz(Quiz quiz) {
        LOGGER.info("Updating quiz");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, quiz.getName());
            statement.setString(2, quiz.getDescription());
            statement.setInt(3, quiz.getDifficulty().ordinal());
            statement.setInt(4, quiz.getTime());
            statement.setInt(5, quiz.getNumberOfQuestion());
            statement.setLong(6, quiz.getId());

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Quiz update failed");
            }
            else {
                LOGGER.info("Quiz updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return quiz;
    }

    public boolean deleteQuizById(Long id) {
        LOGGER.info("Deleting quiz");
        boolean res = false;

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Quiz deletion failed");
            }
            else {
                LOGGER.info("Quiz deleted successfully");
                res = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    public Quiz findQuizById(Long id) {
        LOGGER.info("Getting quiz with id " + id);
        Quiz quiz = null;

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                quiz = new Quiz();
                quiz.setId(result.getLong("id"));
                quiz.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return quiz;
    }

    public Quiz findQuizByName(String name) {
        LOGGER.info("Getting quiz with name " + name);
        Quiz quiz = null;

        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByNameQuery);
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                quiz = new Quiz();
                quiz.setId(result.getLong("id"));
                quiz.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return quiz;
    }

    public List<Quiz> findAll(Integer offset, Integer size) {
        LOGGER.info("Getting all quizzes");
        List<Quiz> res = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAllQuery);
            statement.setInt(1, offset);
            statement.setInt(2, size);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(result.getLong("id"));
                quiz.setName(result.getString("name"));
                quiz.setDescription(result.getString("description"));
                quiz.setDifficulty(QuizDifficulty.values()[result.getInt("difficulty")]);
                quiz.setTime(result.getInt("time"));
                quiz.setNumberOfQuestion(result.getInt("numberOfQuestion"));
                res.add(quiz);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }
}
