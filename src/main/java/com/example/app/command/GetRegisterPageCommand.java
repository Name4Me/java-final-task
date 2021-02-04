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
    private static String loginPage;

    public GetRegisterPageCommand(){
        LOGGER.info("Initializing GetRegisterPageCommand");
        MappingProperties properties = MappingProperties.getInstance();
        registerPage = properties.getProperty("registerPage");
        mainPage = properties.getProperty("mainPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = registerPage;
        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
        } else if(request.getParameter("fname") == null && request.getParameter("lname") == null &&
                request.getParameter("email") == null && request.getParameter("password") == null &&
                request.getParameter("address") == null) {
            LOGGER.info("Returning registration page");
            return resultPage;
        }
        return resultPage;
    }
}
