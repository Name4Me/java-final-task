package com.example.app.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {
    private static ConnectionPool connectionPool;

    @BeforeEach
    void setUp() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    void testGetConnection() {
        assertNotNull(connectionPool);
        try(Connection connection = connectionPool.getConnection()) {
            assertTrue(true);
        } catch (Exception e) { fail(); }
    }

    @Test
    void testGetInstance() {
        assertNotNull(connectionPool);
        ConnectionPool connectionPool1 = ConnectionPool.getInstance();
        assertEquals(connectionPool, connectionPool1);
    }

    @Test
    void getLookupName() { assertEquals("java:comp/env/jdbc/mysql", connectionPool.getLookupName()); }

    @Test
    void setLookupName() {
        connectionPool.setLookupName("");
        assertEquals("", connectionPool.getLookupName());
        try(Connection connection = connectionPool.getConnection()) {
        } catch (Exception e) { assertTrue(true); }
    }
}