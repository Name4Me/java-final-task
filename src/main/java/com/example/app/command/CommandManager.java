package com.example.app.command;

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
/*        getCommands.put("/logout", new LogoutCommand());*/
        getCommands.put("/register", new GetRegisterPageCommand());
/*        getCommands.put("/magazine", new GetMagazinePageCommand());
        getCommands.put("/category", new GetCategoryPageCommand());
        getCommands.put("/subscribe", new GetSubscribePageCommand());
        getCommands.put("/account", new GetAccountPageCommand());
        getCommands.put("/image", new GetImageCommand());
        getCommands.put("/search", new GetSearchPageCommand());*/

/*        //admin categories
        getCommands.put("/admin/categories", new CategoriesAdminPageCommand());
        getCommands.put("/admin/categories/add", new AddCategoryAdminPageCommand());
        getCommands.put("/admin/categories/delete", new DeleteCategoryAdminCommand());
        getCommands.put("/admin/categories/edit", new EditCategoryAdminPageCommand());

        //admin publishers
        getCommands.put("/admin/publishers", new PublishersAdminPageCommand());
        getCommands.put("/admin/publishers/edit", new EditPublisherAdminPageCommand());
        getCommands.put("/admin/publishers/add", new AddPublisherAdminPageCommand());
        getCommands.put("/admin/publishers/delete", new DeletePublisherAdminCommand());

        //admin users
        getCommands.put("/admin/users", new UsersAdminPageCommand());
        getCommands.put("/admin/admins", new AdminsAdminPageCommand());
        getCommands.put("/admin/admins/add", new GetAddAdminPageCommand());
        getCommands.put("/admin/user", new GetUserInfoAdminCommand());

        //admin magazines
        getCommands.put("/admin/magazines", new MagazinesAdminPageCommand());
        getCommands.put("/admin/magazines/add", new GetAddMagazineAdminPageCommand());
        getCommands.put("/admin/magazines/edit", new EditMagazineAdminPageCommand());
        getCommands.put("/admin/magazines/delete", new DeleteMagazineAdminCommand());

        //admin subscriptions
        getCommands.put("/admin/subscriptions", new SubscriptionsAdminPageCommand());*/

        //===================POST commands===================

        postCommands.put("/login", new LoginCommand());
        postCommands.put("/register", new RegisterCommand());
/*        postCommands.put("/subscribe", new SubscribeCommand());

        //admin categories
        postCommands.put("/admin/categories/add", new AddCategoryAdminCommand());
        postCommands.put("/admin/categories/update", new UpdateCategoryAdminCommand());

        //admin publishers
        postCommands.put("/admin/publishers/add", new AddPublisgerAdminCommand());
        postCommands.put("/admin/publishers/update", new UpdatePublisherAdminCommand());

        //admin users
        postCommands.put("/admin/admins/add", new AddAdminAdminCommand());

        //admin magazines
        postCommands.put("/admin/magazines/add", new AddMagazineAdminCommand());
        postCommands.put("/admin/magazines/update", new UpdateMagazineAdminCommand());*/


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
