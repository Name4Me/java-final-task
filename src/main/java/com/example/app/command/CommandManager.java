package com.example.app.command;

import com.example.app.command.admin.quizzes.AddQuizCommand;
import com.example.app.command.admin.quizzes.DeleteQuizCommand;
import com.example.app.command.admin.quizzes.QuizzesAdminPageCommand;
import com.example.app.command.admin.quizzes.UpdateQuizCommand;
import com.example.app.command.admin.users.BlockUserCommand;
import com.example.app.command.admin.users.GetUserInfoAdminCommand;
import com.example.app.command.admin.users.UnBlockUserCommand;
import com.example.app.command.admin.users.UsersAdminPageCommand;
import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * This is a helper class that is used to work with servlet commands.
 *
 * It handles mapping of the url paths to the commands.
 */
public class CommandManager {

    private static final Logger LOGGER = Logger.getLogger(CommandManager.class);

    private HashMap<String, ServletCommand> getCommands;
    private HashMap<String, ServletCommand> postCommands;
    private static String errorPage;

    public CommandManager(){
        LOGGER.info("Initializing CommandManager");

        getCommands = new HashMap<>();
        postCommands = new HashMap<>();

        //===================GET commands===================

        getCommands.put("/", new GetMainPageCommand());
        getCommands.put("/login", new GetLoginPageCommand());
        getCommands.put("/logout", new LogoutCommand());
        getCommands.put("/register", new GetRegisterPageCommand());

/*
        getCommands.put("/account", new GetAccountPageCommand());
        */


        //admin users
        getCommands.put("/admin/users", new UsersAdminPageCommand());
        getCommands.put("/admin/user", new GetUserInfoAdminCommand());

        getCommands.put("/admin/quizzes", new QuizzesAdminPageCommand());
/*        getCommands.put("/admin/admins", new AdminsAdminPageCommand());
        getCommands.put("/admin/admins/add", new GetAddAdminPageCommand());
        */

        //===================POST commands===================

        postCommands.put("/login", new LoginCommand());
        postCommands.put("/register", new RegisterCommand());
        postCommands.put("/admin/user/block", new BlockUserCommand());
        postCommands.put("/admin/user/unblock", new UnBlockUserCommand());
        postCommands.put("/admin/quiz/add", new AddQuizCommand());
        postCommands.put("/admin/quiz/delete", new DeleteQuizCommand());
        postCommands.put("/admin/quiz/update", new UpdateQuizCommand());
        MappingProperties properties = MappingProperties.getInstance();
        errorPage = properties.getProperty("errorPage");
    }

    /**
     * This method is used to get a command instance mapped to http get method, based on a request.
     *
     * @param request http request from servlet.
     * @return        A servlet command instance.
     */
    public ServletCommand getGetCommand(HttpServletRequest request) {
        String command = getMappting(request);
        LOGGER.info("Getting command " + command);
        System.out.println("authenticated: "+request.getSession().getAttribute("authenticated"));
        if(getCommands.get(command) == null) {
            return getCommands.get("/");
        }
        return getCommands.get(command);
    }

    /**
     * This method is used to get a command instance mapped to http post method, based on a request.
     *
     * @param request http request from servlet.
     * @return        A servlet command instance.
     */
    public ServletCommand getPostCommand(HttpServletRequest request) {
        String command = getMappting(request);
        LOGGER.info("Getting command " + command);
        if(postCommands.get(command) == null) {
            return getCommands.get("/");
        }
        return postCommands.get(command);
    }

    /**
     * This is a helper method to get command mapping from uri.
     *
     * @param request http request from servlet.
     * @return        Command mapping.
     */
    public String getMappting(HttpServletRequest request) {
        String mapping = request.getRequestURI().substring(request.getContextPath().length());
        if(mapping.endsWith("/")) {
            mapping = mapping.substring(0, mapping.length() - 1);
        }
        return mapping;
    }
}
