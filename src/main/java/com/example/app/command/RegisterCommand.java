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
import java.util.regex.Pattern;

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

	/**
	 * @param request  Http request from servlet.
	 * @param response Http response from servlet.
	 * @return page or json data
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("RegisterCommand executing command");
		boolean result = false;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		if(email != null && Pattern.compile(regex).matcher(email).matches() && password != null
				&& password.length() > 0 && password.equals(confirmPassword)
				&& userService.checkEmailAvailability(email)){
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
				session.setAttribute("userId", user.getId());
				result = true;
			}
		}
		return new Gson().toJson(result);
	}
}
