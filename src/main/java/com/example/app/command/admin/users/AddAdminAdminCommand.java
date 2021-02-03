package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.user.MysqlUserDaoImpl;
import com.example.app.model.user.User;
import com.example.app.model.user.UserBuilder;
import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import com.example.app.service.user.UserService;
import com.example.app.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to create admin.
 */
public class AddAdminAdminCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(AddAdminAdminCommand.class);

	private static UserService userService;

	private static String addAdminPage;

	public AddAdminAdminCommand(){
		LOGGER.info("Initializing AddAdminAdminCommand");

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		addAdminPage = properties.getProperty("adminAddAdminPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = addAdminPage;

		if(request.getParameter("fname") != null && request.getParameter("lname") != null &&
				request.getParameter("email") != null && request.getParameter("password") != null &&
				userService.checkEmailAvailability(request.getParameter("email"))) {

			User user = new UserBuilder().setFirstName(request.getParameter("fname"))
					                     .setEmail(request.getParameter("email"))
					                     .setPassword(request.getParameter("password"))
					                     .setAddress("")
					                     .setUserType(UserType.ADMIN)
					                     .build();

			request.setAttribute("success", userService.registerUser(user));
		}

		return resultPage;
	}
}
