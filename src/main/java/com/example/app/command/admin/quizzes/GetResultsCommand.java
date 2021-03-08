package com.example.app.command.admin.quizzes;

import com.example.app.command.ServletCommand;
import com.example.app.dao.TestResultDao;
import com.example.app.util.PdfHelper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to get pdf.
 */
public class GetResultsCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(GetResultsCommand.class);

    public GetResultsCommand() {
        LOGGER.info("Initializing GetResultsCommand");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("GetResultsCommand executing");
        response.setContentType("application/pdf");
        try {
            PdfHelper.createPdf(response.getOutputStream(), TestResultDao.getInstance().findAll());
        } catch (Exception e) {
            LOGGER.error("GetResultsCommand: " + e.getMessage());
        }
        return null;
    }
}
