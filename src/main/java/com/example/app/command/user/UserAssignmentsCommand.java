package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserQuizService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to GET requests to the admin page to view quizzes.
 */
public class UserAssignmentsCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserAssignmentsCommand.class);
    private static UserQuizService userQuizService;
    private static String page;

    public UserAssignmentsCommand(){
        LOGGER.info("Initializing UserAssignmentsCommand");
        userQuizService = new UserQuizService(UserQuizDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("assignmentsPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserAssignmentsCommand executing command");
        try {
            HttpSession session = request.getSession();
            Page<UserQuiz> page = userQuizService.getPageByUserId(
                    Math.toIntExact((int) session.getAttribute("userId")),
                    request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")),
                    request.getParameter("size") == null ? 5 :Integer.parseInt(request.getParameter("size")));
            request.setAttribute("page", page);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;
    }
}
