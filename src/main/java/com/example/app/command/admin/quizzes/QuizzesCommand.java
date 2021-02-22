package com.example.app.command.admin.quizzes;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import com.example.app.service.QuizService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to GET requests to the admin page to view users.
 */
public class QuizzesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(QuizzesCommand.class);

    private static QuizService quizService;

    private static String page;

    public QuizzesCommand(){
        LOGGER.info("Initializing UsersAdminPageCommand");

        quizService = new QuizService(QuizDao.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminQuizzesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        try {
            Integer pageNum = 1;
            if (request.getParameter("p") != null) {
                pageNum = Integer.parseInt(request.getParameter("p"));
            }
            //Integer size = Integer.parseInt(request.getParameter("s"));
            Page<Quiz> page = quizService.getPageByQuiz(UserType.ADMIN, pageNum, 5);

            request.setAttribute("page", page);
        }
        catch (Exception e) { LOGGER.info("QuizzesCommand: " + e.getMessage()); }

        return page;
    }
}
