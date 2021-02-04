package com.example.app.dao.quiz;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.quiz.Quiz;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlQuizDaoImpl implements QuizDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlQuizDaoImpl.class);

    private static MysqlQuizDaoImpl INSTANCE;
    private static ConnectionPool connectionPool;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String findByNameQuery;
    private static String findAllQuery;

    private MysqlQuizDaoImpl() {
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

    public static MysqlQuizDaoImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MysqlQuizDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        LOGGER.info("Creating new quiz");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, quiz.getName());

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

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        LOGGER.info("Updating quiz");

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, quiz.getName());
            statement.setLong(2, quiz.getId());

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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Quiz> findAll() {
        LOGGER.info("Getting all quizzes");
        List<Quiz> res = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAllQuery);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(result.getLong("id"));
                quiz.setName(result.getString("name"));

                res.add(quiz);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }
}
