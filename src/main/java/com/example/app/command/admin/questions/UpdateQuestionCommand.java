package com.example.app.command.admin.questions;

import com.example.app.command.ServletCommand;
import com.example.app.dao.question.QuestionDao;
import com.example.app.dao.quiz.MysqlQuizDaoImpl;
import com.example.app.model.question.Question;
import com.example.app.model.question.QuestionType;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.quiz.QuizDifficulty;
import com.example.app.service.question.QuestionService;
import com.example.app.service.quiz.QuizService;
import com.example.app.service.quiz.QuizServiceImpl;
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
		if(request.getParameter("quizId") != null && request.getParameter("text") != null &&
				request.getParameter("type") != null && request.getParameter("id") != null) {
			question.setQuizId(Integer.parseInt(request.getParameter("quizId")));
			question.setText(request.getParameter("text"));
			question.setType(QuestionType.values()[Integer.parseInt(request.getParameter("type"))]);
			question.setId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("success", questionService.updateQuestion(question));
		}

		return new Gson().toJson(question);
	}
}
