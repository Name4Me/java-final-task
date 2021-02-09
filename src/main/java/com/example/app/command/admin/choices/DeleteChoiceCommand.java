package com.example.app.command.admin.choices;

import com.example.app.command.ServletCommand;
import com.example.app.dao.choice.ChoiceDao;
import com.example.app.service.choice.ChoiceService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to delete choice.
 */
public class DeleteChoiceCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(DeleteChoiceCommand.class);
	private static ChoiceService choiceService;

	public DeleteChoiceCommand(){
		LOGGER.info("Initializing DeleteChoiceCommand");
		choiceService = new ChoiceService(ChoiceDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("DeleteChoiceCommand executing");
		JsonObject json = new JsonObject();
		if(request.getParameter("id") != null) {
			json.addProperty("result", choiceService.deleteChoice(Integer.parseInt(request.getParameter("id"))));
		}

		return json.toString();
	}
}
