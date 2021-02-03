package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the page to create admin.
 */
public class GetAddAdminPageCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetAddAdminPageCommand.class);

	private static String addAdminPage;
	private static String loginPage;

	public GetAddAdminPageCommand(){
		LOGGER.info("Initializing GetAddAdminPageCommand");

		MappingProperties properties = MappingProperties.getInstance();
		addAdminPage = properties.getProperty("adminAddAdminPage");
		loginPage = properties.getProperty("loginPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = addAdminPage;

		if(request.getSession().getAttribute("authenticated") != null &&
				request.getSession().getAttribute("authenticated").equals(true) &&
				!request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
			LOGGER.info("User not authorized");
			resultPage = loginPage;
		}

		return resultPage;
	}
}
