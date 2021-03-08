package com.example.app.command.admin.choices;

import com.example.app.command.ServletCommand;
import com.example.app.dao.ChoiceDao;
import com.example.app.model.Choice;
import com.example.app.service.ChoiceService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to add new choice.
 */
public class AddChoiceCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(AddChoiceCommand.class);
    private static ChoiceService choiceService;

    public AddChoiceCommand() {
        LOGGER.info("Initializing AddChoiceCommand");
        choiceService = new ChoiceService(ChoiceDao.getInstance());
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("AddChoiceCommand executing command");
        Choice choice = new Choice();
        if (request.getParameter("questionId") != null && request.getParameter("text") != null) {
            choice.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
            choice.setText(request.getParameter("text"));
            choice.setIsCorrect(request.getParameter("isCorrect") != null);
            request.setAttribute("success", choiceService.createChoice(choice));
        }

        return new Gson().toJson(choice);
    }
}
