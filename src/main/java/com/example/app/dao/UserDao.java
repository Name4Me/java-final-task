package com.example.app.dao;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.user.User;
import com.example.app.model.user.UserStatus;
import com.example.app.model.user.UserType;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao{
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	private static UserDao INSTANCE;
	private static ConnectionPool connectionPool;
	private static String blockQuery;
	private static String createQuery;
	private static String updateQuery;
	private static String findByIdQuery;
	private static String findByEmailQuery;
	private static String findByEmailAndPasswordQuery;
	private static String findPageByUserType;

	private UserDao() {
		LOGGER.info("Initializing MysqlUserDaoImpl");
		connectionPool = ConnectionPool.getInstance();
		MysqlQueryProperties properties = MysqlQueryProperties.getInstance();
		blockQuery = properties.getProperty("blockUser");
		createQuery = properties.getProperty("createUser");
		updateQuery = properties.getProperty("updateUserById");
		findByIdQuery = properties.getProperty("findUserById");
		findByEmailQuery = properties.getProperty("findUserByEmail");
		findByEmailAndPasswordQuery = properties.getProperty("findUserByEmailAndPassword");
		findPageByUserType = properties.getProperty("findPageByUserType");
	}

	public static UserDao getInstance() {
		if(INSTANCE == null) { INSTANCE = new UserDao(); }
		return INSTANCE;
	}

	public User createUser(User user) {
		LOGGER.info("Creating new user");
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getEmail());
			statement.setBytes(2, user.getPassword());
			if(statement.executeUpdate() != 0) {
				LOGGER.info("User creation successful");
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						user.setId(generatedKeys.getInt(1));
					} else { LOGGER.error("Failed to create user, no ID obtained."); }
				}
			} else { LOGGER.error("User creation failed"); }
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return user;
	}

	public User updateUser(User user) {
		LOGGER.info("Updating user");
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateQuery)) {
			statement.setString(1, user.getEmail());
			statement.setBytes(2, user.getPassword());
			statement.setInt(3, user.getUserType().ordinal());
			statement.setInt(4, user.getId());
			if(statement.execute()) {
				LOGGER.error("User update failed");
			} else { LOGGER.info("User updated successfully"); }
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return user;
	}

	public User findUserById(int id) {
		LOGGER.info("Getting user with id " + id);
		User user = null;
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) { user = getUser(result); }
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return user;
	}

	public User findUserByEmail(String email) {
		LOGGER.info("Getting user with email " + email);
		User user = null;
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByEmailQuery)) {
			statement.setString(1, email);
			try(ResultSet result = statement.executeQuery()) { user = getUser(result); }
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return user;
	}

	public User findUserByEmailAndPassword(String email, byte[] password) {
		LOGGER.info("Getting user with email " + email);
		User user = null;
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByEmailAndPasswordQuery)) {
			statement.setString(1, email);
			statement.setBytes(2, password);
			try(ResultSet result = statement.executeQuery()) { user = getUser(result); }
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }
		return user;
	}

	public List<User> findPageByUserType(UserType userType, Integer offset, Integer size) {
		LOGGER.info("Getting page with offset " + offset + ", size " + size + " of userType " + userType.name());
		List<User> res = new ArrayList<>();
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(findPageByUserType)) {
			statement.setInt(1, userType.ordinal());
			statement.setInt(2, offset);
			statement.setInt(3, size);
			try(ResultSet result = statement.executeQuery()) { res = getUsers(result); }
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return res;
	}

	public boolean blockUser(int id){
		LOGGER.info("blockUser");
		boolean result = false;
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(blockQuery)) {
			statement.setInt(1, UserStatus.BLOCKED.ordinal());
			statement.setInt(2, id);
			if (statement.executeUpdate() != 0) {
				result = true;
				LOGGER.info("blockUser successfully");
			} else { LOGGER.error("blockUser failed"); }
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }
		return result;
	}

	public boolean unblockUser(int id){
		LOGGER.info("unblockUser");
		boolean result = false;
		try(Connection connection = connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(blockQuery)) {
			statement.setInt(1, UserStatus.ACTIVE.ordinal());
			statement.setInt(2, id);
			if (statement.executeUpdate() != 0) {
				result = true;
				LOGGER.info("unblockUser successfully");
			} else { LOGGER.error("unblockUser failed"); }
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }
		return result;
	}


	private List<User> getUsers(ResultSet resultSet) {
		List<User> res = new ArrayList<>();
		try {
			while (resultSet.next()) { res.add(getUser(resultSet)); }
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return res;
	}

	private User getUser(ResultSet resultSet) {
		User user = null;
		try {
			if(resultSet.next()) {
				user = new User(resultSet.getInt("id"),
						resultSet.getString("email"),
						resultSet.getBytes("password"),
						UserType.values()[resultSet.getInt("userType")],
						UserStatus.values()[resultSet.getInt("status")]);
			}
		} catch (Exception e) { LOGGER.error(e.getMessage()); }
		return user;
	}

}
