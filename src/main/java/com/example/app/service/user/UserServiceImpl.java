package com.example.app.service.user;

import com.example.app.dao.user.UserDao;
import com.example.app.model.user.User;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        LOGGER.info("Initializing UserServiceImpl");

        this.userDao = userDao;
    }

    @Override
    public boolean checkEmailAvailability(String email) {
        LOGGER.info("Checking availability of email");

        if(email == null) {
            return false;
        }

        User user = userDao.findUserByEmail(email);
        return user == null;
    }

    @Override
    public boolean registerUser(User user) {
        LOGGER.info("New user registration");

        return user != null && userDao.createUser(user).getId() != null;

    }

    @Override
    public User getUserByCredentials(String email, String password) {
        LOGGER.info("Getting user by credentials");

        if(email == null || password == null) {
            return null;
        }

        return userDao.findUserByEmailAndPassword(email, password);
    }

/*    @Override
    public Page<User> getPageByUserType(Integer page, Integer size, UserType userType) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for user type " + userType.name());

        if(page == null || size == null || page < 1 || size < 1) {
            return null;
        }

        List<User> items =  userDao.findPageByUserType(userType, (page - 1) * size, size);
        return new Page<>(items, page, size);
    }*/

    @Override
    public User findUserByEmail(String email) {
        LOGGER.info("Finding user by email " + email);

        if(email == null) {
            return null;
        }

        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        LOGGER.info("Finding user by id " + id);

        if(id == null) {
            return null;
        }

        return userDao.findUserById(id);
    }
}
