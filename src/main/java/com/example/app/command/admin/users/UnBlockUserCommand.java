package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.user.MysqlUserDaoImpl;
import com.example.app.service.user.UserService;
import com.example.app.service.user.UserServiceImpl;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to create admin.
 */
public class UnBlockUserCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(UnBlockUserCommand.class);

	private static UserService userService;

	public UnBlockUserCommand(){
		LOGGER.info("Initializing UnBlockUserCommand");
		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		JsonObject json = new JsonObject();
		if(request.getParameter("id") != null) {
			json.addProperty("result", userService.unblockUser(Long.parseLong(request.getParameter("id"))));
		}
		return json.toString();
	}
}
