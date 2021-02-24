package com.example.app.service;

import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.model.user.UserStatus;
import com.example.app.model.user.UserType;
import com.example.app.util.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final byte[] PASSWORD = new byte[]{1,2,3};
    private static final String EMAIL = "test@test.com";
    private final User user = new User(1, EMAIL, PASSWORD, UserType.USER, UserStatus.ACTIVE);
    private UserService userService;
    @Mock private UserDao userDao;

    @BeforeEach
    void setUp() { userService = new UserService(userDao); }

    @Test
    void checkEmailAvailability() {
        Mockito.when(userDao.findUserByEmail(EMAIL)).thenReturn(user);
        boolean actual = userService.checkEmailAvailability(EMAIL);
        assertFalse(actual);
        Mockito.verify(userDao).findUserByEmail(EMAIL);
    }

    @Test
    void registerUser() {
        Mockito.when(userDao.createUser(user)).thenReturn(user);
        boolean actual = userService.registerUser(user);
        assertTrue(actual);
        Mockito.verify(userDao).createUser(user);
    }

    @Test
    void getUserByCredentials() {
        Mockito.when(userDao.findUserByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(user);
        User actual = userService.getUserByCredentials(EMAIL, PASSWORD);
        assertEquals(user, actual);
        Mockito.verify(userDao).findUserByEmailAndPassword(EMAIL, PASSWORD);
    }

    @Test
    void getPageByUserType() {
        List<User> items =  new ArrayList<>();
        items.add(user);
        Mockito.when(userDao.findPageByUserType(UserType.USER, 0, 6)).thenReturn(items);
        Page<User> actual = userService.getPageByUserType(1, 5, UserType.USER);
        assertEquals(user, actual.getItems().get(0));
        Mockito.verify(userDao).findPageByUserType(UserType.USER, 0, 6);
    }

    @Test
    void getPageByUserTypeWithNull() {
        Page<User> actual = userService.getPageByUserType(null, 5, UserType.USER);
        assertNull(actual);
    }

    @Test
    void findUserByEmail() {
        Mockito.when(userDao.findUserByEmail(EMAIL)).thenReturn(user);
        User actual = userService.findUserByEmail(EMAIL);
        assertEquals(user, actual);
        Mockito.verify(userDao).findUserByEmail(EMAIL);
    }

    @Test
    void findUserById() {
        Mockito.when(userDao.findUserById(1)).thenReturn(user);
        User actual = userService.findUserById(1);
        assertEquals(user, actual);
        Mockito.verify(userDao).findUserById(1);
    }

    @Test
    void blockUser() {
        Mockito.when(userDao.blockUser(1)).thenReturn(true);
        boolean actual = userService.blockUser(1);
        assertTrue(actual);
        Mockito.verify(userDao).blockUser(1);
    }

    @Test
    void unblockUser() {
        Mockito.when(userDao.unblockUser(1)).thenReturn(true);
        boolean actual = userService.unblockUser(1);
        assertTrue(actual);
        Mockito.verify(userDao).unblockUser(1);
    }

}