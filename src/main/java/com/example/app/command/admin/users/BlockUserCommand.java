package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserDao;
import com.example.app.service.UserService;
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
		userService = new UserService(UserDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		JsonObject json = new JsonObject();
		if(request.getParameter("id") != null) {
			json.addProperty("result", userService.blockUser(Integer.parseInt(request.getParameter("id"))));
		}
		return json.toString();
	}
}
