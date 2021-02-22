package com.example.app.command.admin.questions;

import com.example.app.command.ServletCommand;
import com.example.app.dao.QuestionDao;
import com.example.app.model.question.Question;
import com.example.app.properties.MappingProperties;
import com.example.app.service.QuestionService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionsCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(QuestionsCommand.class);
    private static QuestionService questionService;
    private static String page;

    public QuestionsCommand(){
        LOGGER.info("Initializing QuestionsCommand");
        questionService = new QuestionService(QuestionDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminQuestionPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("QuestionsCommand executing command");
        try {
            Page<Question> page = null;
            int quizId = request.getParameter("quizId") == null ? 0 : Integer.parseInt(request.getParameter("quizId"));
            if (quizId != 0 ){
                page = questionService.getPage(
                    quizId,
                    request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")),
                    request.getParameter("size") == null ? 5 : Integer.parseInt(request.getParameter("size"))
                );
            }
            request.setAttribute("quizId", quizId);
            request.setAttribute("page", page);
        }
        catch (Exception e) { LOGGER.info("QuestionsCommand: " + e.getMessage()); }
        return page;
    }
}
