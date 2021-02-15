package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuestionDao;
import com.example.app.dao.QuizDao;
import com.example.app.dao.UserQuizDao;
import com.example.app.model.Choice;
import com.example.app.model.question.Question;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.model.userQuize.UserQuizStatus;
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

        try {
            HttpSession session = request.getSession();
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            int userId = Math.toIntExact((int) session.getAttribute("userId"));

            UserQuiz userQuiz = userQuizService.getUserQuizByUserIdQuizId(userId, quizId,false);

            if (userQuiz.getStatus() != UserQuizStatus.NotStarted) {
                request.setAttribute("errorMessage", "You can not restart quiz.");
                return "/";
            }

            userQuiz = userQuizService.getUserQuizByUserIdQuizId(userId, quizId,true);

            List<Integer> answersForCheck = new ArrayList<>();
            int[] answers = new int[userQuiz.getQuiz().getNumberOfQuestion()];
            for ( Question question : userQuiz.getQuiz().getQuestions() ) {
                int idx = 0;
                int r = 0;
                for ( Choice choice : question.getChoices() ) {
                    r += (choice.getIsCorrect() ? 1 : 0) << idx++;
                }
                answersForCheck.add(r);
            }
            session.setAttribute("answersForCheck", answersForCheck);
            session.setAttribute("numberOfQuestion", userQuiz.getQuiz().getNumberOfQuestion());
            session.setAttribute("answers", answers);
            session.setAttribute("quizId", quizId);
            request.setAttribute("isJson", false);
            request.setAttribute("quiz", userQuiz);
            userQuiz.setStatus(UserQuizStatus.Started);
            userQuizService.updateUserQuiz(userQuiz);
        }
        catch (Exception e) { LOGGER.info("Couldn't parse request parameters " + e.getMessage()); }
        return page;//new Gson().toJson(userQuiz);
    }
}
