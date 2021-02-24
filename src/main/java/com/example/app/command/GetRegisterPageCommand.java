package com.example.app.command;

import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to get user registration page.
 */
public class GetRegisterPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(GetRegisterPageCommand.class);
    private static String registerPage;
    private static String mainPage;

    public GetRegisterPageCommand(){
        LOGGER.info("Initializing GetRegisterPageCommand");
        MappingProperties properties = MappingProperties.getInstance();
        registerPage = properties.getProperty("registerPage");
        mainPage = properties.getProperty("mainPage");
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return page
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = registerPage;
        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
        } else if(request.getParameter("email") == null && request.getParameter("password") == null) {
            LOGGER.info("Returning registration page");
            return resultPage;
        }
        return resultPage;
    }
}
