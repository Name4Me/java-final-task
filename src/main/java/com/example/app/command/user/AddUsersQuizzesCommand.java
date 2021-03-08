package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.AssignmentDao;
import com.example.app.model.assignment.Assignment;
import com.example.app.service.AssignmentService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to handle POST requests to add new user.
 */
public class AddUsersQuizzesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(AddUsersQuizzesCommand.class);
    private static AssignmentService assignmentService;

    public AddUsersQuizzesCommand() {
        LOGGER.info("Initializing AddQuestionCommand");
        assignmentService = new AssignmentService(AssignmentDao.getInstance());
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return json result.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("AddQuestionCommand executing command");
        Assignment assignment = new Assignment();
        HttpSession session = request.getSession();
        if (session.getAttribute("blocked") != null) {
            request.setAttribute("errorMessage", "A blocked user cannot do this.");
            return "/";
        }
        if (request.getParameter("quizId") != null && session.getAttribute("userId") != null) {
            assignment.setUserId(Math.toIntExact((int) session.getAttribute("userId")));
            assignment.setQuizId(Integer.parseInt(request.getParameter("quizId")));
            request.setAttribute("success", assignmentService.createUserQuiz(assignment));
        }

        return new Gson().toJson(assignment);
    }
}
