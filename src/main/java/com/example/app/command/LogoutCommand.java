package com.example.app.command;

import com.example.app.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to user logout.
 */
public class LogoutCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(GetLoginPageCommand.class);

/*    private static CategoryService categoryService;
    private static MagazineService magazineService;*/

    private static String mainPage;

    public LogoutCommand(){
        LOGGER.info("Initializing LogoutCommand");

/*        categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());
        magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
                                                  MysqlImageDaoImpl.getInstance());*/

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        LOGGER.info("Logging out user " + request.getSession().getAttribute("email"));

        request.getSession().invalidate();

/*        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));*/

        return mainPage;
    }
}
