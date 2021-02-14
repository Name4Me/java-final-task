package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.service.UserQuizService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to handle POST requests to add new quiz.
 */
public class AddUsersQuizzesCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AddUsersQuizzesCommand.class);
	private static UserQuizService userQuizService;

	public AddUsersQuizzesCommand(){
		LOGGER.info("Initializing AddQuestionCommand");
		userQuizService = new UserQuizService(UserQuizDao.getInstance());
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("AddQuestionCommand executing command");
		UserQuiz userQuiz = new UserQuiz();
		HttpSession session = request.getSession();
		if(request.getParameter("quizId") != null && session.getAttribute("userId") != null) {
			userQuiz.setUserId(Math.toIntExact((int) session.getAttribute("userId")));
			userQuiz.setQuizId(Integer.parseInt(request.getParameter("quizId")));
			request.setAttribute("success", userQuizService.createUserQuiz(userQuiz));
		}

		return new Gson().toJson(userQuiz);
	}
}
