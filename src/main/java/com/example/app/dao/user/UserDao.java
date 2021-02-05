package com.example.app.dao.user;

import com.example.app.model.user.User;
import com.example.app.model.user.UserType;
import java.util.List;

public interface UserDao {
	/**
	 * This method created new user.
	 *
	 * @param user User to be created.
	 * @return     Updated user object.
	 */
	User createUser(User user);

	/**
	 * This method updates user.
	 *
	 * @param user User to be updated.
	 * @return     Updated user object.
	 */
	User updateUser(User user);

	/**
	 * This method deletes user by id.
	 *
	 * @param id Id of the user to delete.
	 */
	void deleteUserById(Long id);

	/**
	 * This method finds user by id.
	 *
	 * @param id Id of the user to find.
	 * @return   User object found by id, otherwise null.
	 */
	User findUserById(Long id);

	/**
	 * This method finds user by email.
	 *
	 * @param email Email to find user by.
	 * @return      User object found by email, otherwise null.
	 */
	User findUserByEmail(String email);

	/**
	 * This method finds user by email and password.
	 *
	 * @param email    Email of user to find.
	 * @param password Password of user to find.
	 * @return         User object if user found, otherwise null.
	 */
	User findUserByEmailAndPassword(String email, String password);

	boolean blockUser(Long id);

	boolean unblockUser(Long id);

	/**
	 * This method returns a page of users by user type.
	 *
	 * @param userType User type of users to find.
	 * @param offset   Element to start from.
	 * @param size     How much elements to take.
	 * @return         List of users.
	 */
	List<User> findPageByUserType(UserType userType, Integer offset, Integer size);


}
