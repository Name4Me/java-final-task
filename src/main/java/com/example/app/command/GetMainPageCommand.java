package com.example.app.command;

import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the main page.
 */
public class GetMainPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(GetMainPageCommand.class);
    private static String page;

    public GetMainPageCommand(){
        LOGGER.info("Initializing GetMainPageCommand");
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        if(request.getParameter("locale") != null) {
            String locale = request.getParameter("locale");
            switch (locale) {
                case "en":
                    request.getSession().setAttribute("locale", "en");
                    break;
                case "ru":
                    request.getSession().setAttribute("locale", "ru");
                    break;
            }
        }
        return page;
    }
}
