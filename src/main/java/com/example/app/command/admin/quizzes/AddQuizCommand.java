package com.example.app.command.admin.quizzes;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.quiz.QuizDifficulty;
import com.example.app.service.QuizService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to add new quiz.
 */
public class AddQuizCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AddQuizCommand.class);
	private static QuizService quizService;

	public AddQuizCommand(){
		LOGGER.info("Initializing AddQuizCommand");
		quizService = new QuizService(QuizDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		Quiz quiz = new Quiz();
		if(request.getParameter("name") != null) {

			quiz.setName(request.getParameter("name"));
			quiz.setDescription(request.getParameter("description"));
			quiz.setDifficulty(QuizDifficulty.values()[Integer.parseInt(request.getParameter("difficulty"))]);
			quiz.setTime(Integer.parseInt(request.getParameter("time")));
			quiz.setNumberOfQuestion(Integer.parseInt(request.getParameter("numberOfQuestion")));
			request.setAttribute("success", quizService.createQuiz(quiz));
		}

		return new Gson().toJson(quiz);
	}
}
