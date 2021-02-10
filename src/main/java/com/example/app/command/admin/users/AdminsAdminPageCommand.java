package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserDao;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserService;
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

		userService = new UserService(UserDao.getInstance());

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
