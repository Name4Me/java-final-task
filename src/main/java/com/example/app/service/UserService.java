package com.example.app.service;

import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.model.user.UserType;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private UserDao userDao;

    public UserService(UserDao userDao) {
        LOGGER.info("Initializing UserServiceImpl");

        this.userDao = userDao;
    }

    public boolean checkEmailAvailability(String email) {
        LOGGER.info("Checking availability of email");

        if(email == null) {
            return false;
        }

        User user = userDao.findUserByEmail(email);
        return user == null;
    }

    public boolean registerUser(User user) {
        LOGGER.info("New user registration");
        return user != null && userDao.createUser(user).getId() != null;

    }

    public User getUserByCredentials(String email, byte[] password) {
        LOGGER.info("Getting user by credentials");

        if(email == null || password == null) {
            return null;
        }

        return userDao.findUserByEmailAndPassword(email, password);
    }

    public Page<User> getPageByUserType(Integer page, Integer size, UserType userType) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for user type " + userType.name());

        if(page == null || size == null || page < 1 || size < 1) {
            return null;
        }

        List<User> items =  userDao.findPageByUserType(userType, (page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    public User findUserByEmail(String email) {
        LOGGER.info("Finding user by email " + email);

        if(email == null) {
            return null;
        }

        return userDao.findUserByEmail(email);
    }

    public User findUserById(Long id) {
        LOGGER.info("Finding user by id " + id);

        if(id == null) {
            return null;
        }

        return userDao.findUserById(id);
    }

    public boolean blockUser(Long id) {
        LOGGER.info("Block user by id " + id);
        return id == null ? false : userDao.blockUser(id);

    }

    public boolean unblockUser(Long id) {
        LOGGER.info("Unblock user by id " + id);
        return id == null ? false : userDao.unblockUser(id);
    }
}
