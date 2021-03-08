package com.example.app.command.user;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.properties.MappingProperties;
import com.example.app.service.QuizService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to GET requests to the admin page to view quizzes.
 */
public class UserQuizzesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UserQuizzesCommand.class);
    private static QuizService quizService;
    private static String page;
    private static String pageAjax;

    public UserQuizzesCommand() {
        LOGGER.info("Initializing UserQuizzesCommand");
        quizService = new QuizService(QuizDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("quizzesPage");
        pageAjax = properties.getProperty("quizzesAjaxPage");
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return page.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UserQuizzesCommand executing command");
        try {
            String colName = request.getParameter("sortField") == null ? "id" : request.getParameter("sortField");
            String direction = request.getParameter("sortOrder") == null ? "asc" : request.getParameter("sortOrder");
            colName = "id name difficulty numberOfQuestion".contains(colName) ? colName : "id";
            direction = "asc desc".contains(direction) ? direction : "asc";
            HttpSession session = request.getSession();
            int userId = session.getAttribute("userId") == null ? 0 : (int) session.getAttribute("userId");
            Page<Quiz> page = quizService.getPageByUserIdWithSort(
                    userId, colName, direction,
                    request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")),
                    request.getParameter("size") == null ? 5 : Integer.parseInt(request.getParameter("size")));
            request.setAttribute("page", page);
        } catch (Exception e) {
            LOGGER.info("Couldn't parse request parameters " + e.getMessage());
        }
        if (request.getParameter("sortOrder") != null) {

            request.setAttribute("isJson", false);
            return pageAjax;
        }
        return page;
    }
}
