package com.example.app.command;

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
 * This class is used to handle POST requests to register user.
 */
public class RegisterCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);
	private static UserService userService;
	private static String registerPage;
	private static String mainPage;

	public RegisterCommand(){
		LOGGER.info("Initializing RegisterCommand");
		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());
		MappingProperties properties = MappingProperties.getInstance();
		registerPage = properties.getProperty("registerPage");
		mainPage = properties.getProperty("mainPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = registerPage;
		if(request.getParameter("firstName") != null &&
			request.getParameter("email") != null && request.getParameter("password") != null &&
			userService.checkEmailAvailability(request.getParameter("email"))){
			LOGGER.info("New user registration");
			User user = new UserBuilder().setFirstName(request.getParameter("firstName"))
					                     .setEmail(request.getParameter("email"))
					                     .setPassword(request.getParameter("password"))
					                     .setUserType(UserType.USER)
					                     .build();
			if(userService.registerUser(user)) {
				resultPage = mainPage;
			}
		}
		return resultPage;
	}
}
