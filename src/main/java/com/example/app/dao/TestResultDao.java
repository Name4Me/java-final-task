package com.example.app.dao;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.TestResult;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestResultDao {
    private static final Logger LOGGER = Logger.getLogger(TestResultDao.class);
    private static TestResultDao INSTANCE;
    private static ConnectionPool connectionPool;
    private static String getQuery;


    public TestResultDao() {
        LOGGER.info("Initializing TestResultDao");
        connectionPool = ConnectionPool.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();
        getQuery = properties.getProperty("getResults");
    }

    public static TestResultDao getInstance() {
        if(INSTANCE == null) { INSTANCE = new TestResultDao(); }
        return INSTANCE;
    }

    public List<TestResult> findAll() {
        List<TestResult> res = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(getQuery)) {
            LOGGER.info(statement.toString());
            try(ResultSet result = statement.executeQuery()) {
                res = getTestResults(result);
            }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private List<TestResult> getTestResults(ResultSet resultSet) {
        List<TestResult> res = new ArrayList<>();
        try {
            while (resultSet.next()) {
                res.add(getTestResult(resultSet));
                LOGGER.info("getTestResults");
            }
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return res;
    }

    private TestResult getTestResult(ResultSet resultSet) {
        TestResult testResult = null;
        try {
            testResult = new TestResult(resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getInt("score"));
            LOGGER.info(testResult.toString());
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return testResult;
    }

}
