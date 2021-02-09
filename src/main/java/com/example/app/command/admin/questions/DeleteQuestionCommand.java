package com.example.app.command.admin.questions;

import com.example.app.command.ServletCommand;
import com.example.app.dao.question.QuestionDao;
import com.example.app.dao.quiz.MysqlQuizDaoImpl;
import com.example.app.service.question.QuestionService;
import com.example.app.service.quiz.QuizService;
import com.example.app.service.quiz.QuizServiceImpl;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to delete quiz.
 */
public class DeleteQuestionCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(DeleteQuestionCommand.class);
	private static QuestionService questionService;

	public DeleteQuestionCommand(){
		LOGGER.info("Initializing DeleteQuizCommand");
		questionService = new QuestionService(QuestionDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command Delete Quiz");
		JsonObject json = new JsonObject();
		if(request.getParameter("id") != null) {
			json.addProperty("result", questionService.deleteQuestion(Integer.parseInt(request.getParameter("id"))));
		}

		return json.toString();
	}
}
