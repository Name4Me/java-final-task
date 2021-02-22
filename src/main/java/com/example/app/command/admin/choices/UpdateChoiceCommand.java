package com.example.app.command.admin.choices;

import com.example.app.command.ServletCommand;
import com.example.app.dao.ChoiceDao;
import com.example.app.model.Choice;
import com.example.app.service.ChoiceService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to update choice.
 */
public class UpdateChoiceCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(UpdateChoiceCommand.class);
	private static ChoiceService choiceService;

	public UpdateChoiceCommand(){
		LOGGER.info("Initializing UpdateQuestionCommand");
		choiceService = new ChoiceService(ChoiceDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("UpdateChoiceCommand executing command");
		Choice choice = new Choice();
		if(request.getParameter("questionId") != null && request.getParameter("text") != null &&
				request.getParameter("id") != null) {
			choice.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
			choice.setText(request.getParameter("text"));
			choice.setIsCorrect(request.getParameter("isCorrect") == null ? false : true);
			choice.setId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("success", choiceService.updateChoice(choice));
		}

		return new Gson().toJson(choice);
	}
}
