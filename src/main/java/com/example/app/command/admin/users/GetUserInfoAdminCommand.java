package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserService;
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
		userService = new UserService(UserDao.getInstance());
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
				User user = userService.findUserById(Integer.parseInt(request.getParameter("id")));
				if(user != null) {
					request.setAttribute("user", user);
					resultPage = userInfoPage;
				}
			}
			catch (Exception e) { LOGGER.error(e.getMessage()); }
		}
		return resultPage;
	}
}
