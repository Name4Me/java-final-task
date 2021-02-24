package com.example.app.util;

import com.example.app.model.TestResult;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.apache.log4j.Logger;

import java.io.OutputStream;
import java.util.List;

public class PdfHelper {
    private static final Logger LOGGER = Logger.getLogger(PdfHelper.class);

    public static void createPdf(OutputStream stream, List<TestResult> items) {
        try {
            Font bfBold18 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0));
            Document document = new Document();
            PdfWriter.getInstance(document, stream);
            document.open();
            Paragraph title = new Paragraph("Test results...", bfBold18);
            title.setIndentationLeft(50);
            document.add(title);

            LineSeparator separator = new LineSeparator();
            Chunk linebreak = new Chunk(separator);
            document.add(linebreak);

            PdfPTable table = new PdfPTable(new float[]{5, 28, 60, 7});
            int i = 1;
            for (TestResult result : items) {
                PdfPCell cell = new PdfPCell(Phrase.getInstance(String.valueOf(i++)));
                cell.setBorder(PdfPCell.BOTTOM);
                cell.setPaddingBottom(5);
                table.addCell(cell);
                PdfPCell cell1 = new PdfPCell(Phrase.getInstance(result.getEmail()));
                cell1.setBorder(PdfPCell.BOTTOM);
                cell1.setPaddingBottom(5);
                table.addCell(cell1);
                PdfPCell cell2 = new PdfPCell(Phrase.getInstance(result.getTestName()));
                cell2.setBorder(PdfPCell.BOTTOM);
                cell2.setPaddingBottom(5);
                table.addCell(cell2);
                PdfPCell cell3 = new PdfPCell(Phrase.getInstance(String.valueOf(result.getScore())));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setBorder(PdfPCell.BOTTOM);
                cell3.setPaddingBottom(5);
                table.addCell(cell3);
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            LOGGER.error("PdfHelper.createPdf: " + e.getMessage());
        }

    }
}
