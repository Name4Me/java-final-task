package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserDao;
import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to GET requests to the admin page to view users.
 */
public class UsersAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UsersAdminPageCommand.class);
    private static UserService userService;
    private static String page;

    public UsersAdminPageCommand(){
        LOGGER.info("Initializing UsersAdminPageCommand");
        userService = new UserService(UserDao.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminUsersPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UsersAdminPageCommand executing command");
        try {
            int pageNum = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            int size = request.getParameter("size") == null ? 5 : Integer.parseInt(request.getParameter("size"));
            request.setAttribute("page", userService.getPageByUserType(pageNum, size, UserType.USER));
        }
        catch (Exception e) { LOGGER.error("UsersAdminPageCommand: " + e.getMessage()); }
        return page;
    }
}
