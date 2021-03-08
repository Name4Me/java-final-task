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
 * This class is used to handle POST requests to update quiz.
 */
public class UpdateQuizCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UpdateQuizCommand.class);
    private static QuizService quizService;

    public UpdateQuizCommand() {
        LOGGER.info("Initializing AddQuizCommand");
        quizService = new QuizService(QuizDao.getInstance());
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return json result.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        Quiz quiz = new Quiz();
        if (request.getParameter("name") != null && request.getParameter("id") != null) {
            quiz.setName(request.getParameter("name"));
            quiz.setDescription(request.getParameter("description"));
            quiz.setDifficulty(QuizDifficulty.values()[Integer.parseInt(request.getParameter("difficulty"))]);
            quiz.setTime(Integer.parseInt(request.getParameter("time")));
            quiz.setNumberOfQuestion(Integer.parseInt(request.getParameter("numberOfQuestion")));
            quiz.setId(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("success", quizService.updateQuiz(quiz));
        }

        return new Gson().toJson(quiz);
    }
}
