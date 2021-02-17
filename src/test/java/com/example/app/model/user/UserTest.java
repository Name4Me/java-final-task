package com.example.app.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private static final byte[] PASSWORD = new byte[]{1,2,3};
    private static final byte[] NEW_PASSWORD = new byte[]{1,2,3,4};
    private static final String EMAIL = "test@test.com";
    private static final String NEW_EMAIL = "new@test.com";
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User(1, EMAIL, PASSWORD, UserType.USER, UserStatus.ACTIVE);
    }

    @Test
    void emptyConstructor() {
        assertEquals(0, (new User()).getId());
    }

    @Test
    void shortConstructor() {
        user = new User(EMAIL, PASSWORD, UserType.USER);
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertEquals(EMAIL, this.user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(UserType.USER, user.getUserType());
    }

    @Test
    void getId() { assertEquals(1, user.getId()); }

    @Test
    void setId() {
        user.setId(10);
        assertEquals(10, user.getId());
    }

    @Test
    void getPassword() { assertEquals(PASSWORD, user.getPassword()); }

    @Test
    void setPassword() {
        user.setPassword(NEW_PASSWORD);
        assertEquals(NEW_PASSWORD, user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals(EMAIL, this.user.getEmail());
    }

    @Test
    void setEmail() {
        user.setEmail(NEW_EMAIL);
        assertEquals(NEW_EMAIL, user.getEmail());
    }

    @Test
    void getUserType() {
        assertEquals(UserType.USER, user.getUserType());
    }

    @Test
    void setUserType() {
        user.setUserType(UserType.ADMIN);
        assertEquals(UserType.ADMIN, user.getUserType());
    }

    @Test
    void testEquals() {
        User user2 = new User(1, EMAIL, PASSWORD, UserType.USER, UserStatus.ACTIVE);
        assertEquals(user, user2);
    }

    @Test
    void testHashCode() {
        assertNotEquals(0, user.hashCode());
    }

    @Test
    void getUserStatus() {
        assertEquals(UserStatus.ACTIVE, user.getUserStatus());
    }

    @Test
    void setUserStatus() {
        user.setUserStatus(UserStatus.BLOCKED);
        assertEquals(UserStatus.BLOCKED, user.getUserStatus());
    }

}