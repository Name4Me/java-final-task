package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.model.user.UserType;
import com.example.app.properties.MappingProperties;
import com.example.app.service.UserService;
import com.example.app.util.Page;
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
        LOGGER.info("Executing command");

        try {
            /*Integer pageNum = Integer.parseInt(request.getParameter("p"));
            Integer size = Integer.parseInt(request.getParameter("s"));*/
            Integer pageNum = 1;
            if (request.getParameter("p") != null) {
                pageNum = Integer.parseInt(request.getParameter("p"));
            }
            Page<User> page = userService.getPageByUserType(pageNum, 5, UserType.USER);

            request.setAttribute("page", page);
        }
        catch (NumberFormatException ex) {
            LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
                    + request.getParameter("s") +" to long");
        }

        return page;
    }
}
