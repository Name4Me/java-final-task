package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.AssignmentDao;
import com.example.app.model.assignment.Assignment;
import com.example.app.model.assignment.AssignmentStatus;
import com.example.app.properties.MappingProperties;
import com.example.app.service.AssignmentService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class is used to POST requests assignment.
 */
public class UserAssignmentCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserAssignmentCommand.class);
    private static AssignmentService assignmentService;
    private static String assignmentPage;
    private static String assignmentsPage;
    public UserAssignmentCommand(){
        LOGGER.info("Initializing UserAssignmentCommand");
        assignmentService = new AssignmentService(AssignmentDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        assignmentPage = properties.getProperty("assignmentPage");
        assignmentsPage = properties.getProperty("assignmentsPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserAssignmentCommand executing command");
        try {
            HttpSession session = request.getSession();
            int quizId = request.getParameter("quizId") == null ? session.getAttribute("quizId") == null ? 0 :
                (int) session.getAttribute("quizId") : Integer.parseInt(request.getParameter("quizId"));
            int userId = session.getAttribute("userId") == null ? 0 : (int) session.getAttribute("userId");
            if (request.getParameter("action") != null && "complete".equals(request.getParameter("action"))){
                Assignment assignment = assignmentService.getUserQuizByUserIdQuizId(userId, quizId, false);
                assignment.setStatus(AssignmentStatus.Completed);
                assignmentService.updateUserQuiz(assignment);
                return new Gson().toJson("ok");
            }

            if (request.getParameter("action") != null && "saveAnswer".equals(request.getParameter("action"))){
                int result = Integer.parseInt(request.getParameter("result"));
                int id = Integer.parseInt(request.getParameter("id"))-1;
                int[] answers = (int[]) session.getAttribute("answers");
                int numberOfQuestion = (int) session.getAttribute("numberOfQuestion");
                List<Integer> answersForCheck = (List<Integer>) session.getAttribute("answersForCheck");
                answers[id] = answersForCheck.get(id) == result ? 1 : 0;
                double score = 0;
                for ( int a : answers ) { score += 100/numberOfQuestion * a; }
                System.out.println("score: "+score);
                session.setAttribute("answers", answers);
                session.setAttribute("score", score);
                Assignment assignment = assignmentService.getUserQuizByUserIdQuizId(userId, quizId, false);
                assignment.setScore((int) score);
                assignmentService.updateUserQuiz(assignment);
            }

            Assignment assignment = assignmentService.getUserQuizByUserIdQuizId( userId, quizId, true);
            request.setAttribute("isJson", false);
            request.setAttribute("quiz", assignment);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return assignmentPage;//new Gson().toJson(userQuiz);
    }
}
