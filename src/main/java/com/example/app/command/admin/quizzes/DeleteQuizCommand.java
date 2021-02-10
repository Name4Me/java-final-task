package com.example.app.command.admin.quizzes;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuizDao;
import com.example.app.service.QuizService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to delete quiz.
 */
public class DeleteQuizCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(DeleteQuizCommand.class);
	private static QuizService quizService;

	public DeleteQuizCommand(){
		LOGGER.info("Initializing DeleteQuizCommand");
		quizService = new QuizService(QuizDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("DeleteQuizCommand executing");
		JsonObject json = new JsonObject();
		if(request.getParameter("id") != null) {
			json.addProperty("result", quizService.deleteQuiz(Long.parseLong(request.getParameter("id"))));
		}

		return json.toString();
	}
}
