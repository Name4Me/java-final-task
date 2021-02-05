package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.user.MysqlUserDaoImpl;
import com.example.app.model.user.User;
import com.example.app.model.user.UserBuilder;
import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import com.example.app.service.user.UserService;
import com.example.app.service.user.UserServiceImpl;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to create admin.
 */
public class BlockUserCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(BlockUserCommand.class);

	private static UserService userService;

	public BlockUserCommand(){
		LOGGER.info("Initializing BlockUserCommand");
		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		JsonObject json = new JsonObject();
		if(request.getParameter("id") != null) {
			json.addProperty("result", userService.blockUser(Long.parseLong(request.getParameter("id"))));
		}
		return json.toString();
	}
}
