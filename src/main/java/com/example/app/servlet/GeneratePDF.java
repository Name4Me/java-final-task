package com.example.app.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.collection.PdfTargetDictionary;

public class GeneratePDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GeneratePDF() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("application/pdf");
        Document document = new Document();
        Font bfBold18 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0));
        Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, new BaseColor(0, 0, 0));
        Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
        try{
            PdfWriter.getInstance(document, os);

            document.addAuthor("Roman Tsapyk");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("test.com");
            document.addTitle("Results");
            document.setPageSize(PageSize.LETTER);
            document.open();

            //add a new paragraph
            document.add( new Paragraph("Test results...", bfBold18));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(8);
            for(int aw = 0; aw < 16; aw++){
                table.addCell("hi");
            }
            document.add(table);


            document.close();

        }catch(DocumentException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
