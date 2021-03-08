package com.example.app.command;

import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.model.user.UserStatus;
import com.example.app.service.UserService;
import com.example.app.util.Password;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * This class is used to handle POST requests to authenticate users.
 */
public class LoginCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static UserService userService;

    public LoginCommand() {
        LOGGER.info("Initializing LoginCommand");
        userService = new UserService(UserDao.getInstance());
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return page or json data
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        JsonObject json = new JsonObject();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (email != null && Pattern.compile(regex).matcher(email).matches() && password != null && password.length() > 0) {
            User user = userService.getUserByCredentials(email, Password.getPasswordHash(password));
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("email", user.getEmail());
                session.setAttribute("userId", user.getId());
                session.setAttribute("authenticated", true);
                session.setAttribute("role", user.getUserType().name());
                if (user.getUserStatus() == UserStatus.BLOCKED) {
                    session.setAttribute("blocked", true);
                }
                json.addProperty("result", true);
            } else {
                json.addProperty("result", false);
            }
        }
        return json.toString();
    }
}
