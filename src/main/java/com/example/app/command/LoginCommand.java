package com.example.app.command;

import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.service.UserService;
import com.example.app.util.Password;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to handle POST requests to authenticate users.
 */
public class LoginCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
	private static UserService userService;

	public LoginCommand(){
		LOGGER.info("Initializing LoginCommand");
		userService = new UserService(UserDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		JsonObject json = new JsonObject();
		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			User user = userService.getUserByCredentials(request.getParameter("email"), Password.getPasswordHash(request.getParameter("password")) );
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("email", user.getEmail());
				session.setAttribute("username", user.getFirstName());
				session.setAttribute("userId", user.getId());
				session.setAttribute("authenticated", true);
				session.setAttribute("role", user.getUserType().name());
				json.addProperty("result", true);
			}
			else {
				json.addProperty("result", false);
			}
		}
		return json.toString();
	}
}
