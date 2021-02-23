package com.example.app.util;

import com.example.app.model.TestResult;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Pdf {
    public void createPdf(String dest, List<TestResult> items) throws IOException, DocumentException {
        Font bfBold18 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0));
        File file = new File(dest);

        file.getParentFile().mkdirs();

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph title = new Paragraph("Test results...", bfBold18);
        title.setIndentationLeft(50);
        document.add(title);

        LineSeparator separator = new LineSeparator();
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);

        PdfPTable table = new PdfPTable(new float[] { 5, 28, 60, 7 });
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
            PdfPCell cell2= new PdfPCell(Phrase.getInstance(result.getTestName()));
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
    }
}
