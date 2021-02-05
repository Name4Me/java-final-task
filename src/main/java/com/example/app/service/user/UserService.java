package com.example.app.service.user;

import com.example.app.model.user.User;
import com.example.app.model.user.UserType;
import com.example.app.util.Page;

public interface UserService {
    /**
     * This method check weather email is already used.
     *
     * @param email Email to check.
     * @return      True if email is unused, otherwise false.
     */
    boolean checkEmailAvailability(String email);

    /**
     * This method registers new user.
     *
     * @param user User to register.
     * @return     Updated user object.
     */
    boolean registerUser(User user);

    /**
     * This method gets user object based on credentials.
     *
     * @param email    User email.
     * @param password User password.
     * @return         User object if user is found, otherwise null.
     */
    User getUserByCredentials(String email, String password);

    /**
     * This method returns a page of users by user type.
     *
     * @param page     Number of the page, starts from 1.
     * @param size     Size of the page.
     * @param userType User type of the users to find.
     * @return         A list of users.
     */
    Page<User> getPageByUserType(Integer page, Integer size, UserType userType);

    /**
     * This method finds user by email.
     *
     * @param email Email of the user to find.
     * @return      User found by email, otherwise null.
     */
    User findUserByEmail(String email);

    /**
     * This method finds user by id.
     *
     * @param id Id of user to find.
     * @return   User found by id, otherwise null;
     */
    User findUserById(Long id);

    boolean blockUser(Long id);
    boolean unblockUser(Long id);
}
