package com.example.app.command.admin.users;

import com.example.app.command.ServletCommand;
import com.example.app.dao.UserDao;
import com.example.app.model.user.User;
import com.example.app.model.user.UserStatus;
import com.example.app.service.UserService;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to update user.
 */
public class UpdateUserCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UpdateUserCommand.class);
    private static UserService userService;

    public UpdateUserCommand() {
        LOGGER.info("Initializing UpdateUserCommand");
        userService = new UserService(UserDao.getInstance());
    }

    /**
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return json result.
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UpdateUserCommand executing command");
        JsonObject json = new JsonObject();
        int userId = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email") == null ? null : request.getParameter("email");
        String status = request.getParameter("status") == null ? null : request.getParameter("status");
        if (userId != 0) {
            User user = userService.findUserById(userId);
            boolean doUpdate = false;
            if (user != null) {
                if (email != null && !user.getEmail().equals(email) && userService.checkEmailAvailability(email)) {
                    user.setEmail(email);
                    doUpdate = true;
                }
                if (status != null && "ACTIVE BLOCKED".contains(status) && !user.getUserStatus().toString().equals(status)) {
                    user.setUserStatus("ACTIVE".equals(status) ? UserStatus.ACTIVE : UserStatus.BLOCKED);
                    doUpdate = true;
                }
                if (doUpdate) {
                    json.addProperty("result", userService.updateUser(user));
                }
            }

        }
        return json.toString();
    }
}
