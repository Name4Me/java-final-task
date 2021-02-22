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
		String text = request.getParameter("text");
		int quizId = request.getParameter("quizId") == null ? 0 : Integer.parseInt(request.getParameter("quizId"));
		if(quizId != 0 && text != null && text.length()!=0) {
			question.setQuizId(quizId);
			question.setText(text);
			question.setType(QuestionType.Single);
			request.setAttribute("success", questionService.createQuestion(question));
		}

		return new Gson().toJson(question);
	}
}
