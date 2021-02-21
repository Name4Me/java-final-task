package com.example.app.command;

import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.model.user.UserType;
import com.example.app.service.UserService;
import com.example.app.util.Password;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to handle POST requests to register user.
 */
public class RegisterCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);
	private static UserService userService;

	public RegisterCommand(){
		LOGGER.info("Initializing RegisterCommand");
		userService = new UserService(UserDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("RegisterCommand executing command");
		boolean result = false;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email != null && password != null &&
			userService.checkEmailAvailability(email)){
			LOGGER.info("New user registration");
			User user = new User(
					request.getParameter("email"),
					Password.getPasswordHash(request.getParameter("password")),
					UserType.USER);
			if(userService.registerUser(user)) {
				HttpSession session = request.getSession();
				session.setAttribute("email", user.getEmail());
				session.setAttribute("authenticated", true);
				session.setAttribute("role", user.getUserType().name());
				result = true;
			}
		}
		return new Gson().toJson(result);
	}
}
