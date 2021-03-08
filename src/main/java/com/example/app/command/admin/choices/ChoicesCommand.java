package com.example.app.command.admin.choices;

import com.example.app.command.ServletCommand;
import com.example.app.dao.ChoiceDao;
import com.example.app.model.Choice;
import com.example.app.properties.MappingProperties;
import com.example.app.service.ChoiceService;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle requests to receive choices.
 */
public class ChoicesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(ChoicesCommand.class);
    private static ChoiceService choiceService;
    private static String page;

    public ChoicesCommand() {
        LOGGER.info("Initializing ChoicesCommand");
        choiceService = new ChoiceService(ChoiceDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminChoicePage");
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return page.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ChoicesCommand executing command");
        try {
            Page<Choice> page;
            int questionId = request.getParameter("questionId") == null ? 0 :
                    Integer.parseInt(request.getParameter("questionId"));
            int quizId = request.getParameter("quizId") == null ? 0 :
                    Integer.parseInt(request.getParameter("quizId"));
            if (questionId != 0) {
                page = choiceService.getPage(
                        questionId,
                        request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")),
                        request.getParameter("size") == null ? 5 : Integer.parseInt(request.getParameter("size"))
                );
                request.setAttribute("questionId", questionId);
                request.setAttribute("quizId", quizId);
                request.setAttribute("page", page);
            } else {
                request.setAttribute("errorMessage", "Incorrect request");
            }
        } catch (Exception e) {
            LOGGER.info("ChoicesCommand: " + e.getMessage());
        }

        return page;
    }
}
