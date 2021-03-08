package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.AssignmentDao;
import com.example.app.model.Choice;
import com.example.app.model.assignment.Assignment;
import com.example.app.model.question.Question;
import com.example.app.model.assignment.AssignmentStatus;
import com.example.app.properties.MappingProperties;
import com.example.app.service.AssignmentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to start quiz.
 */
public class UserStartQuizCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserStartQuizCommand.class);
    private static AssignmentService assignmentService;
    private static String page;

    public UserStartQuizCommand() {
        LOGGER.info("Initializing UserStartQuizCommand");
        assignmentService = new AssignmentService(AssignmentDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("quizPage");
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return page.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserStartQuizCommand executing command");
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("blocked") != null) {
                request.setAttribute("errorMessage", "A blocked user cannot do this.");
                return "/";
            }
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            int userId = (int) session.getAttribute("userId");
            Assignment assignment = assignmentService.getUserQuizByUserIdQuizId(userId, quizId, false);
            if (assignment.getStatus() != AssignmentStatus.NotStarted) {
                request.setAttribute("errorMessage", "You can not restart quiz.");
                return "/";
            }
            assignment = assignmentService.getUserQuizByUserIdQuizId(userId, quizId, true);
            List<Integer> answersForCheck = new ArrayList<>();
            int[] answers = new int[assignment.getQuiz().getNumberOfQuestion()];
            for (Question question : assignment.getQuiz().getQuestions()) {
                int idx = 0;
                int r = 0;
                for (Choice choice : question.getChoices()) {
                    r += (choice.getIsCorrect() ? 1 : 0) << idx++;
                }
                answersForCheck.add(r);
            }
            session.setAttribute("answersForCheck", answersForCheck);
            session.setAttribute("numberOfQuestion", assignment.getQuiz().getNumberOfQuestion());
            session.setAttribute("answers", answers);
            session.setAttribute("quizId", quizId);
            request.setAttribute("isJson", false);
            request.setAttribute("assignment", assignment);
            assignment.setStatus(AssignmentStatus.Started);
            assignmentService.updateUserQuiz(assignment);
        } catch (Exception e) {
            LOGGER.info("Couldn't parse request parameters " + e.getMessage());
        }
        return page;
    }
}
