package com.example.app.util;

import com.example.app.model.user.User;
import com.example.app.model.user.UserStatus;
import com.example.app.model.user.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {
    private static final byte[] PASSWORD = new byte[]{1,2,3};
    private static final String EMAIL = "test@test.com";
    private final User user = new User(1, EMAIL, PASSWORD, UserType.USER, UserStatus.ACTIVE);
    Page<User> page;
    List<User> items;

    @BeforeEach
    void setUp() {
        items =  new ArrayList<>();
        items.add(user);
        page = new Page<>(items, 1, 5);
    }

    @Test
    void getItems() {
        assertEquals(items, page.getItems());
    }

    @Test
    void getNumber() {
        assertEquals(1, page.getNumber());
    }

    @Test
    void getSize() {
        assertEquals(5, page.getSize());
    }

    @Test
    void getCurrentSize() {
        assertEquals(1, page.getCurrentSize());
    }

    @Test
    void isFirst() {
        assertTrue(page.isFirst());
    }

    @Test
    void isLast() {
        assertTrue(page.isLast());
    }
}