package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.QuizService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to GET requests to the admin page to view quizzes.
 */
public class UserQuizzesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserQuizzesCommand.class);
    private static QuizService quizService;
    private static String page;

    public UserQuizzesCommand(){
        LOGGER.info("Initializing UserQuizzesCommand");
        quizService = new QuizService(QuizDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("quizzesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserQuizzesCommand executing command");
        try {
            HttpSession session = request.getSession();
            Page<Quiz> page = quizService.getPageByUserId(
                    Math.toIntExact((Long) session.getAttribute("userId")),
                    request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")),
                    request.getParameter("size") == null ? 5 :Integer.parseInt(request.getParameter("size")));
            request.setAttribute("page", page);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;
    }
}
