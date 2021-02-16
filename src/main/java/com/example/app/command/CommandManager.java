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
import com.example.app.command.user.*;
import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        getCommands.put("/controller", new GetMainPageCommand());
        getCommands.put("/controller/", new GetMainPageCommand());
        getCommands.put("/controller/setLocale", new GetMainPageCommand());
        getCommands.put("/controller/login", new GetLoginPageCommand());
        getCommands.put("/controller/logout", new LogoutCommand());
        getCommands.put("/controller/register", new GetRegisterPageCommand());
        getCommands.put("/controller/user/quizzes", new UserQuizzesCommand());
        getCommands.put("/controller/user/assignments", new UserAssignmentsCommand());
/*
        getCommands.put("/account", new GetAccountPageCommand());
        */


        //admin users
        getCommands.put("/controller/admin/users", new UsersAdminPageCommand());
        getCommands.put("/controller/admin/user", new GetUserInfoAdminCommand());
        getCommands.put("/controller/admin/quizzes", new QuizzesCommand());
        //getCommands.put("/admin/choices", new ChoicesAdminPageCommand());
        getCommands.put("/controller/admin/questions", new QuestionsCommand());
        getCommands.put("/controller/admin/choices", new ChoicesCommand());
        getCommands.put("/controller/user/assignments/assignment", new UserStartQuizCommand());

        //===================POST commands===================

        postCommands.put("/controller/login", new LoginCommand());
        postCommands.put("/controller/register", new RegisterCommand());
        postCommands.put("/controller/admin/user/block", new BlockUserCommand());
        postCommands.put("/controller/admin/user/unblock", new UnBlockUserCommand());
        postCommands.put("/controller/admin/quiz/add", new AddQuizCommand());
        postCommands.put("/controller/admin/quiz/delete", new DeleteQuizCommand());
        postCommands.put("/controller/admin/quiz/update", new UpdateQuizCommand());

        postCommands.put("/controller/admin/question/add", new AddQuestionCommand());
        postCommands.put("/controller/admin/question/delete", new DeleteQuestionCommand());
        postCommands.put("/controller/admin/question/update", new UpdateQuestionCommand());

        postCommands.put("/controller/admin/choice/add", new AddChoiceCommand());
        postCommands.put("/controller/admin/choice/delete", new DeleteChoiceCommand());
        postCommands.put("/controller/admin/choice/update", new UpdateChoiceCommand());

        postCommands.put("/controller/user/quizzes/add", new AddUsersQuizzesCommand());
        postCommands.put("/controller/user/assignments/assignment", new UserAssignmentCommand());
        postCommands.put("/controller/user/quizzes", new UserQuizzesCommand());


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
        //System.out.println("authenticated: "+request.getSession().getAttribute("authenticated"));
        if(getCommands.get(command) == null) {
            return getCommands.get("/controller");
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
            return getCommands.get("/controller");
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
        if(mapping.endsWith("/") && mapping.length() > 1) {
            mapping = mapping.substring(0, mapping.length() - 1);
        }
        return mapping;
    }
}
