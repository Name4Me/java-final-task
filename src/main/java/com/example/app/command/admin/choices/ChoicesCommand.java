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

public class ChoicesCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(ChoicesCommand.class);

    private static ChoiceService choiceService;

    private static String page;

    public ChoicesCommand(){
        LOGGER.info("Initializing ChoicesCommand");
        choiceService = new ChoiceService(ChoiceDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminChoicePage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ChoicesCommand executing command");

        try {
            int pageNum = 1;
            if (request.getParameter("page") != null) {
                pageNum = Integer.parseInt(request.getParameter("page"));
            }
            Integer questionId = Integer.parseInt(request.getParameter("questionId"));
            //Integer size = Integer.parseInt(request.getParameter("s"));
            Page<Choice> page = choiceService.getPage(questionId, pageNum, 5);
            request.setAttribute("questionId", questionId);
            request.setAttribute("page", page);
        }
        catch (NumberFormatException ex) {
            LOGGER.info("ChoicesCommand couldn't parse " + request.getParameter("page") + ", "
                    + request.getParameter("s") +" to long");
        }

        return page;
    }
}
