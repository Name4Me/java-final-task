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
 * This class is used to GET requests to the admin page to view users.
 */
public class UserQuizzesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserQuizzesCommand.class);

    private static QuizService quizService;

    private static String page;

    public UserQuizzesCommand(){
        LOGGER.info("Initializing UserQuizzesCommand");

        quizService = new QuizServiceImpl(MysqlQuizDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("quizzesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        try {
            Integer pageNum = 1;
            if (request.getParameter("p") != null) {
                pageNum = Integer.parseInt(request.getParameter("p"));
            }
            //Integer size = Integer.parseInt(request.getParameter("s"));
            Page<Quiz> page = quizService.getPageByQuiz(pageNum, 5);

            request.setAttribute("page", page);
        }
        catch (NumberFormatException ex) {
            LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
                    + request.getParameter("s") +" to long");
        }

        return page;
    }
}
