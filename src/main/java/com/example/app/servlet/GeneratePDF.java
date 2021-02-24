package com.example.app.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.dao.TestResultDao;
import com.example.app.util.PdfHelper;
import org.apache.log4j.Logger;

public class GeneratePDF extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GeneratePDF.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        try {
            PdfHelper.createPdf(response.getOutputStream(), TestResultDao.getInstance().findAll());
        } catch (Exception e) {
            LOGGER.error("GeneratePDF: " + e.getMessage());
        }

    }
}
