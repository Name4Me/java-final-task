package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserQuizService;
import com.example.app.util.Page;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to POST requests assignment.
 */
public class UserAssignmentCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserAssignmentCommand.class);
    private static UserQuizService userQuizService;
    private static String page;

    public UserAssignmentCommand(){
        LOGGER.info("Initializing UserAssignmentCommand");
        userQuizService = new UserQuizService(UserQuizDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("assignmentPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserAssignmentCommand executing command");
        System.out.println(request.getHeader("content-type"));
        if (request.getParameter("quizId") == null) return "{'error':500}";
        UserQuiz userQuiz = null;
        try {
            HttpSession session = request.getSession();
            userQuiz = userQuizService.getUserQuizByUserIdQuizId(
                    Math.toIntExact((Long) session.getAttribute("userId")),
                    Integer.parseInt(request.getParameter("quizId")),
                    false
            );
            request.setAttribute("isJson", false);
            request.setAttribute("quiz", userQuiz);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;//new Gson().toJson(userQuiz);
    }
}
