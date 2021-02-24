package com.example.app.servlet;

import com.example.app.command.CommandManager;
import com.example.app.command.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Servlet.class);
    private CommandManager commandManager;

    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("Initializing Servlet");
        commandManager = new CommandManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Processing get request");
        ServletCommand command = commandManager.getGetCommand(request);
        String addr = commandManager.getMapping(request);
        String shortAddr = addr.replaceAll("/controller", "");
        LOGGER.info("Processing get request: " + addr);
        if ((request.getSession().getAttribute("authenticated") == null ||
                request.getSession().getAttribute("authenticated").equals(false)) &&
                !("/login /register /setLocale / /user/quizzes".contains(shortAddr))) {
            try {
                response.sendRedirect(request.getContextPath() + "/controller/login");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String page = command.execute(request, response);
        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Processing post request");
        ServletCommand command = commandManager.getPostCommand(request);
        String page = command.execute(request, response);
        if (page != null) {
            if (request.getAttribute("isJson") != null && (boolean) request.getAttribute("isJson") == false) {
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(page);
            }
        }
    }
}
