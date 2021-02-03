package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.user.MysqlUserDaoImpl;
import com.example.app.model.user.User;
import com.example.app.properties.MappingProperties;
import com.example.app.service.user.UserService;
import com.example.app.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserInfoAdminCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetUserInfoAdminCommand.class);

	private static UserService userService;

	private static String usersPage;
	private static String userInfoPage;

	public GetUserInfoAdminCommand(){
		LOGGER.info("Initializing GetUserInfoAdminCommand");

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		usersPage = properties.getProperty("adminUsersPage");
		userInfoPage = properties.getProperty("adminUserInfoPage");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = usersPage;

		if(request.getParameter("id") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				User user = userService.findUserById(id);

				if(user != null) {

					request.setAttribute("user", user);
					resultPage = userInfoPage;
				}
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse id " + request.getParameter("p") + " to long");
			}
		}

		return resultPage;
	}
}
