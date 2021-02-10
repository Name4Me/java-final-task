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
            int pageNum = 1;
            if (request.getParameter("page") != null) {
                pageNum = Integer.parseInt(request.getParameter("page"));
            }
            Integer quizId = Integer.parseInt(request.getParameter("quizId"));
            //Integer size = Integer.parseInt(request.getParameter("s"));
            Page<Question> page = questionService.getPage(quizId, pageNum, 5);
            request.setAttribute("quizId", quizId);
            request.setAttribute("page", page);
        }
        catch (NumberFormatException ex) {
            LOGGER.info("QuestionsCommand couldn't parse " + request.getParameter("page") + ", "
                    + request.getParameter("s") +" to long");
        }

        return page;
    }
}
