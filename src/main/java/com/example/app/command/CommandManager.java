package com.example.app.command;

import com.example.app.command.admin.choices.AddChoiceCommand;
import com.example.app.command.admin.choices.ChoicesCommand;
import com.example.app.command.admin.choices.DeleteChoiceCommand;
import com.example.app.command.admin.choices.UpdateChoiceCommand;
import com.example.app.command.admin.questions.AddQuestionCommand;
import com.example.app.command.admin.questions.DeleteQuestionCommand;
import com.example.app.command.admin.questions.QuestionsCommand;
import com.example.app.command.admin.questions.UpdateQuestionCommand;
import com.example.app.command.admin.quizzes.AddQuizCommand;
import com.example.app.command.admin.quizzes.DeleteQuizCommand;
import com.example.app.command.admin.quizzes.QuizzesCommand;
import com.example.app.command.admin.quizzes.UpdateQuizCommand;
import com.example.app.command.admin.users.BlockUserCommand;
import com.example.app.command.admin.users.GetUserInfoAdminCommand;
import com.example.app.command.admin.users.UnBlockUserCommand;
import com.example.app.command.admin.users.UsersAdminPageCommand;
import com.example.app.command.user.AddUsersQuizzesCommand;
import com.example.app.command.user.UserAssignmentCommand;
import com.example.app.command.user.UserAssignmentsCommand;
import com.example.app.command.user.UserQuizzesCommand;
import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
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
        getCommands.put("/quizzes", new UserQuizzesCommand());
        getCommands.put("/assignments", new UserAssignmentsCommand());
/*
        getCommands.put("/account", new GetAccountPageCommand());
        */


        //admin users
        getCommands.put("/admin/users", new UsersAdminPageCommand());
        getCommands.put("/admin/user", new GetUserInfoAdminCommand());
        getCommands.put("/admin/quizzes", new QuizzesCommand());
        //getCommands.put("/admin/choices", new ChoicesAdminPageCommand());
        getCommands.put("/admin/questions", new QuestionsCommand());
        getCommands.put("/admin/choices", new ChoicesCommand());
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

        postCommands.put("/admin/question/add", new AddQuestionCommand());
        postCommands.put("/admin/question/delete", new DeleteQuestionCommand());
        postCommands.put("/admin/question/update", new UpdateQuestionCommand());

        postCommands.put("/admin/choice/add", new AddChoiceCommand());
        postCommands.put("/admin/choice/delete", new DeleteChoiceCommand());
        postCommands.put("/admin/choice/update", new UpdateChoiceCommand());

        postCommands.put("/quizzes/add", new AddUsersQuizzesCommand());
        postCommands.put("/assignments/assignment", new UserAssignmentCommand());


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
