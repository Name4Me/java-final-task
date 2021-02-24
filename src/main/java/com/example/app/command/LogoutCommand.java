package com.example.app.command;

import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to user logout.
 */
public class LogoutCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(GetLoginPageCommand.class);
    private static String mainPage;

    public LogoutCommand(){
        LOGGER.info("Initializing LogoutCommand");
        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return page
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        LOGGER.info("Logging out user " + request.getSession().getAttribute("email"));
        request.getSession().invalidate();
        return mainPage;
    }
}
