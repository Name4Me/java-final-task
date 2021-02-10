package com.example.app.command.admin.questions;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuestionDao;
import com.example.app.model.question.Question;
import com.example.app.model.question.QuestionType;
import com.example.app.service.QuestionService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to add new quiz.
 */
public class AddQuestionCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AddQuestionCommand.class);
	private static QuestionService questionService;

	public AddQuestionCommand(){
		LOGGER.info("Initializing AddQuestionCommand");
		questionService = new QuestionService(QuestionDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AddQuestionCommand executing command");
		Question question = new Question();
		if(request.getParameter("quizId") != null && request.getParameter("text") != null && request.getParameter("type") != null) {
			question.setQuizId(Integer.parseInt(request.getParameter("quizId")));
			question.setText(request.getParameter("text"));
			question.setType(QuestionType.values()[Integer.parseInt(request.getParameter("type"))]);
			request.setAttribute("success", questionService.createQuestion(question));
		}

		return new Gson().toJson(question);
	}
}
