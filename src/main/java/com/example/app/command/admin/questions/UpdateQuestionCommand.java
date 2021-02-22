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
 * This class is used to handle POST requests to update quiz.
 */
public class UpdateQuestionCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(UpdateQuestionCommand.class);
	private static QuestionService questionService;

	public UpdateQuestionCommand(){
		LOGGER.info("Initializing UpdateQuestionCommand");
		questionService = new QuestionService(QuestionDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("UpdateQuestionCommand executing command");
		Question question = new Question();
		String text = request.getParameter("text");
		int quizId = request.getParameter("quizId") == null ? 0 : Integer.parseInt(request.getParameter("quizId"));
		int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
		if(id != 0 && quizId != 0 && text != null && text.length()!=0) {
			question.setQuizId(Integer.parseInt(request.getParameter("quizId")));
			question.setText(request.getParameter("text"));
			question.setId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("success", questionService.updateQuestion(question));
		}

		return new Gson().toJson(question);
	}
}
