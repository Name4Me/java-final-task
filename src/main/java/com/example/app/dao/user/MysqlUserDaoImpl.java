package com.example.app.dao.user;

import com.example.app.connection.ConnectionPool;
import com.example.app.model.user.User;
import com.example.app.model.user.UserBuilder;
import com.example.app.model.user.UserType;
import com.example.app.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDaoImpl implements UserDao{
	private static final Logger LOGGER = Logger.getLogger(MysqlUserDaoImpl.class);

	private static MysqlUserDaoImpl INSTANCE;
	private static ConnectionPool connectionPool;

	private static String createQuery;
	private static String updateQuery;
	private static String deleteQuery;
	private static String findByIdQuery;
	private static String findByEmailQuery;
	private static String findByEmailAndPasswordQuery;
	private static String findPageByUserType;

	private MysqlUserDaoImpl() {
		LOGGER.info("Initializing MysqlUserDaoImpl");

		connectionPool = ConnectionPool.getInstance();
		MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

		createQuery = properties.getProperty("createUser");
		updateQuery = properties.getProperty("updateUserById");
		deleteQuery = properties.getProperty("deleteUserById");
		findByIdQuery = properties.getProperty("findUserById");
		findByEmailQuery = properties.getProperty("findUserByEmail");
		findByEmailAndPasswordQuery = properties.getProperty("findUserByEmailAndPassword");
		findPageByUserType = properties.getProperty("findPageByUserType");
	}

	public static MysqlUserDaoImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MysqlUserDaoImpl();
		}
		return INSTANCE;
	}

	@Override
	public User createUser(User user) {
		LOGGER.info("Creating new user");

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			int affectedRows = statement.executeUpdate();

			if(affectedRows == 0) {
				LOGGER.info("User creation failed");
			}
			else {
				LOGGER.info("User creation successful");

				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						user.setId(generatedKeys.getLong(1));
					}
					else {
						LOGGER.error("Failed to create user, no ID obtained.");
					}
				}
			}
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }

		return user;
	}

	@Override
	public User updateUser(User user) {
		LOGGER.info("Updating user");

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setLong(4, user.getUserType().ordinal());
			statement.setLong(5, user.getId());

			boolean result = statement.execute();

			if(result) {
				LOGGER.info("User update failed");
			}
			else {
				LOGGER.info("User updated successfully");
			}
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }

		return user;
	}

	@Override
	public void deleteUserById(Long id) {
		LOGGER.info("Deleting user");

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
			statement.setLong(1, id);

			boolean result = statement.execute();

			if(result) {
				LOGGER.info("User deletion failed");
			}
			else {
				LOGGER.info("User deleted successfully");
			}
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }
	}

	@Override
	public User findUserById(Long id) {
		LOGGER.info("Getting user with id " + id);
		User user = null;

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(findByIdQuery);
			statement.setLong(1, id);

			ResultSet result = statement.executeQuery();

			user = getUser(result);
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }

		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		LOGGER.info("Getting user with email " + email);
		User user = null;

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(findByEmailQuery);
			statement.setString(1, email);

			ResultSet result = statement.executeQuery();

			user = getUser(result);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return user;
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		LOGGER.info("Getting user with email " + email);
		User user = null;

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(findByEmailAndPasswordQuery);
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet result = statement.executeQuery();

			user = getUser(result);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return user;
	}

	@Override
	public List<User> findPageByUserType(UserType userType, Integer offset, Integer size) {
		LOGGER.info("Getting page with offset " + offset + ", size " + size + " of userType " + userType.name());
		List<User> res = new ArrayList<>();

		try(Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(findPageByUserType);
			statement.setLong(1, userType.ordinal());
			statement.setInt(2, offset);
			statement.setInt(3, size);
			res = getUsers(statement.executeQuery());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return res;
	}


	private User getUser(ResultSet resultSet) {
		User user = null;

		try {
			if(resultSet.next()) {
				user = new UserBuilder().setId(resultSet.getLong("id"))
						                .setFirstName(resultSet.getString("login"))
						                .setEmail(resultSet.getString("email"))
						                .setPassword(resultSet.getString("password"))
						                .setUserType(UserType.values()[resultSet.getInt("userType")])
						                .build();
            }
		} catch (SQLException e) { LOGGER.info(e.getMessage()); }

		return user;
	}

	private List<User> getUsers(ResultSet resultSet) {
		List<User> res = new ArrayList<>();

		try {
			while (resultSet.next()) {
				User user = new UserBuilder().setId(resultSet.getLong("id"))
						                            .setFirstName(resultSet.getString("login"))
						                            .setEmail(resultSet.getString("email"))
						                            .setUserType(UserType.values()[resultSet.getInt("userType")])
						                            .build();

				res.add(user);
			}
		} catch (SQLException e) { LOGGER.error(e.getMessage()); }

		return res;
	}


}
