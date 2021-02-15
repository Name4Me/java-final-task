package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.model.userQuize.UserQuizStatus;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserQuizService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to POST requests assignment.
 */
public class UserAssignmentCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserAssignmentCommand.class);
    private static UserQuizService userQuizService;
    private static String assignmentPage;
    private static String assignmentsPage;
    public UserAssignmentCommand(){
        LOGGER.info("Initializing UserAssignmentCommand");
        userQuizService = new UserQuizService(UserQuizDao.getInstance());
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
                UserQuiz userQuiz = userQuizService.getUserQuizByUserIdQuizId(userId, quizId, false);
                userQuiz.setStatus(UserQuizStatus.Completed);
                userQuizService.updateUserQuiz(userQuiz);
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
                UserQuiz userQuiz = userQuizService.getUserQuizByUserIdQuizId(userId, quizId, false);
                userQuiz.setScore((int) score);
                userQuizService.updateUserQuiz(userQuiz);
            }

            UserQuiz userQuiz = userQuizService.getUserQuizByUserIdQuizId( userId, quizId, true);
            request.setAttribute("isJson", false);
            request.setAttribute("quiz", userQuiz);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return assignmentPage;//new Gson().toJson(userQuiz);
    }
}
