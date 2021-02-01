package com.example.app.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface is used to mark classes that are used as commands.
 */
public interface ServletCommand {

    /**
     * This method is called to execute a command.
     *
     * @param request  Http request from servlet.
     * @param response Http response from servlet.
     * @return         A string that represents a view to forward to.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
