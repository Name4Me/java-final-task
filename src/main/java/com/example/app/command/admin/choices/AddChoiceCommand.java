package com.example.app.command.admin.choices;

import com.example.app.command.ServletCommand;
import com.example.app.dao.choice.ChoiceDao;
import com.example.app.dao.question.QuestionDao;
import com.example.app.model.choice.Choice;
import com.example.app.model.question.Question;
import com.example.app.model.question.QuestionType;
import com.example.app.service.choice.ChoiceService;
import com.example.app.service.question.QuestionService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to add new quiz.
 */
public class AddChoiceCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AddChoiceCommand.class);
	private static ChoiceService choiceService;

	public AddChoiceCommand(){
		LOGGER.info("Initializing AddChoiceCommand");
		choiceService = new ChoiceService(ChoiceDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AddChoiceCommand executing command");
		Choice choice = new Choice();
		if(request.getParameter("questionId") != null && request.getParameter("text") != null &&
				request.getParameter("isTrue") != null) {
			choice.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
			choice.setText(request.getParameter("text"));
			choice.setTrue(Boolean.getBoolean(request.getParameter("isTrue")));
			request.setAttribute("success", choiceService.createChoice(choice));
		}

		return new Gson().toJson(choice);
	}
}
