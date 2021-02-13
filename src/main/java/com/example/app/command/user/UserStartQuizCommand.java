package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuestionDao;
import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.QuestionService;
import com.example.app.service.QuizService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to GET requests to the admin page to view quizzes.
 */
public class UserStartQuizCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserStartQuizCommand.class);
    private static QuizService quizService;
    private static QuestionService questionService;
    private static String page;

    public UserStartQuizCommand(){
        LOGGER.info("Initializing UserStartQuizCommand");

        quizService = new QuizService(QuizDao.getInstance());
        questionService = new QuestionService(QuestionDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("startPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserStartQuizCommand executing command");
        if (request.getParameter("quizId") != null) {

        }
        try {
            Page<Quiz> page = quizService.getPageByQuiz(
                    request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")),
                    request.getParameter("size") == null ? 5 :Integer.parseInt(request.getParameter("size")));
            request.setAttribute("page", page);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;
    }
}
