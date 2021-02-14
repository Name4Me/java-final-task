package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserQuizService;
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
    private static String quizPage;

    public UserAssignmentCommand(){
        LOGGER.info("Initializing UserAssignmentCommand");
        userQuizService = new UserQuizService(UserQuizDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        assignmentPage = properties.getProperty("assignmentPage");
        quizPage = properties.getProperty("quizPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserAssignmentCommand executing command");
        //if (request.getParameter("quizId") == null) () return "{'error':500}";
        String page = request.getParameter("start") == null ? assignmentPage : quizPage;
        try {
            HttpSession session = request.getSession();
            int userId = Math.toIntExact((int) session.getAttribute("userId"));
            int quizId = Math.toIntExact((int) session.getAttribute("quizId"));
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

            UserQuiz userQuiz = userQuizService.getUserQuizByUserIdQuizId(
                    Math.toIntExact((int) session.getAttribute("userId")),
                    Integer.parseInt(request.getParameter("quizId")),
                    request.getParameter("start") != null
            );
            request.setAttribute("isJson", false);
            request.setAttribute("quiz", userQuiz);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;//new Gson().toJson(userQuiz);
    }
}
