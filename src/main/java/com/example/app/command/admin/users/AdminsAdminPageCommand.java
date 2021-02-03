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

/**
 * This class is used to GET requests to the admin page to view admins.
 */
public class AdminsAdminPageCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AdminsAdminPageCommand.class);

	private static UserService userService;

	private static String page;

	public AdminsAdminPageCommand(){
		LOGGER.info("Initializing AdminsAdminPageCommand");

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		page = properties.getProperty("adminAdminsPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		try {
			Integer pageNum = Integer.parseInt(request.getParameter("p"));
			Integer size = Integer.parseInt(request.getParameter("s"));
		}
		catch (NumberFormatException ex) {
			LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
					+ request.getParameter("s") +" to long");
		}


		return page;
	}
}
