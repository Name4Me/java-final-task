package com.example.app.connection;

import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool = null;
    private String lookupName = "java:comp/env/jdbc/mysql";

    private ConnectionPool() {
        LOGGER.info("Initializing ConnectionPool class");
    }

    public Connection getConnection() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(lookupName);
            connection = ds.getConnection();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    public static synchronized ConnectionPool getInstance() {
        if (connectionPool == null) connectionPool = new ConnectionPool();
        return connectionPool;
    }

    public String getLookupName() {
        return lookupName;
    }

    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }
}
