package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuestionDao;
import com.example.app.dao.QuizDao;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.Choice;
import com.example.app.model.question.Question;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.QuestionService;
import com.example.app.service.QuizService;
import com.example.app.service.UserQuizService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used to GET requests to the admin page to view quizzes.
 */
public class UserStartQuizCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserStartQuizCommand.class);
    private static UserQuizService userQuizService;
    private static String assignmentPage;
    private static String page;

    public UserStartQuizCommand(){
        LOGGER.info("Initializing UserStartQuizCommand");
        userQuizService = new UserQuizService(UserQuizDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("quizPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserStartQuizCommand executing command");
        //if (request.getParameter("quizId") == null) () return "{'error':500}";

        try {
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            HttpSession session = request.getSession();
            UserQuiz userQuiz = userQuizService.getUserQuizByUserIdQuizId(
                    Math.toIntExact((int) session.getAttribute("userId")),
                    Integer.parseInt(request.getParameter("quizId")),
                    true
            );
            List<Integer> answersForCheck = new ArrayList<>();
            int[] answers = new int[userQuiz.getQuiz().getNumberOfQuestion()];
            int idQuestion = 0;
            for ( Question question : userQuiz.getQuiz().getQuestions() ) {
                int idx = 0;
                int r = 0;
                for ( Choice choice : question.getChoices() ) {
                    r += (choice.getIsCorrect() ? 1 : 0) << idx++;
                }
                //answers[idQuestion++] = r;
                answersForCheck.add(r);
                //System.out.println("idQuestion: " + idQuestion + " r: " + Integer.toBinaryString(r));
            }
            session.setAttribute("answersForCheck", answersForCheck);
            session.setAttribute("numberOfQuestion", userQuiz.getQuiz().getNumberOfQuestion());
            session.setAttribute("answers", answers);
            session.setAttribute("quizId", quizId);
            //System.out.println(session.getAttribute("answersForCheck"));
            request.setAttribute("isJson", false);
            request.setAttribute("quiz", userQuiz);
            userQuiz.setStartedAt(new Date());
            userQuizService.updateUserQuiz(userQuiz);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;//new Gson().toJson(userQuiz);
    }
}
