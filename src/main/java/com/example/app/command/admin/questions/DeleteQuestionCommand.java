package com.example.app.command.admin.questions;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuestionDao;
import com.example.app.service.QuestionService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to delete question.
 */
public class DeleteQuestionCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteQuestionCommand.class);
    private static QuestionService questionService;

    public DeleteQuestionCommand() {
        LOGGER.info("Initializing DeleteQuizCommand");
        questionService = new QuestionService(QuestionDao.getInstance());
    }

	/**
	 * @param request  Http request from servlet.
	 * @param response Http response from servlet.
	 * @return json result.
	 */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command Delete Quiz");
        JsonObject json = new JsonObject();
        if (request.getParameter("id") != null) {
            json.addProperty("result", questionService.deleteQuestion(Integer.parseInt(request.getParameter("id"))));
        }

        return json.toString();
    }
}
