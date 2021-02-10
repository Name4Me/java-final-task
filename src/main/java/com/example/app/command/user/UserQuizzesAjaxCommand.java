package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.quiz.MysqlQuizDaoImpl;
import com.example.app.model.quiz.Quiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.quiz.QuizService;
import com.example.app.service.quiz.QuizServiceImpl;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to POST requests to the admin page to view quizzes.
 */
public class UserQuizzesAjaxCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserQuizzesAjaxCommand.class);
    private static QuizService quizService;
    private static String page;

    public UserQuizzesAjaxCommand(){
        LOGGER.info("Initializing UserQuizzesCommand");
        quizService = new QuizServiceImpl(MysqlQuizDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("quizzesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserQuizzesCommand executing command");
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
