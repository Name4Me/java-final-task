package com.example.app.connection;

import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool = null;

    private ConnectionPool() {
        System.out.println();
        LOGGER.info("Initializing ConnectionPool class");
    }

    public Connection getConnection() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
            connection = ds.getConnection();
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
        return connection;
    }

    public static synchronized ConnectionPool getInstance() {
        if (connectionPool == null) connectionPool = new ConnectionPool();
        return connectionPool;
    }
}
