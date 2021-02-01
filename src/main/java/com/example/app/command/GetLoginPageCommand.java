package com.example.app.command;

import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the login page.
 */
public class GetLoginPageCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(GetLoginPageCommand.class);

    private static String loginPage;
    private static String mainPage;

    public GetLoginPageCommand(){
        LOGGER.info("Initializing GetLoginPageCommand");

        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = loginPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
        }
        else if(request.getParameter("email") == null && request.getParameter("password") == null) {
            LOGGER.info("Returning login page");
            return resultPage;
        }

        return resultPage;
    }
}
