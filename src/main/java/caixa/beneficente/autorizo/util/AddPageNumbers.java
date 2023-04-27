package caixa.beneficente.autorizo.util;

import com.spire.pdf.*;
import com.spire.pdf.automaticfields.PdfCompositeField;
import com.spire.pdf.automaticfields.PdfPageCountField;
import com.spire.pdf.automaticfields.PdfPageNumberField;
import com.spire.pdf.graphics.*;

import java.awt.*;

public class AddPageNumbers {

    public void addNumbers() {
        // Create a PdfDocument instance
        PdfDocument pdf = new PdfDocument();
        // Load the PDF document
        pdf.loadFromFile("C:\\Workspace\\autorizo\\src\\relatorios\\sample1.pdf");

        // Create a PdfTrueTypeFont instance
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("Arial", Font.PLAIN, 10));

        // Create a PdfPageNumberField instance
        PdfPageNumberField pageNumberField = new PdfPageNumberField(font, PdfBrushes.getBlack());

        // Create a PdfPageCountField instance
        PdfPageCountField pageCountField = new PdfPageCountField(font, PdfBrushes.getBlack());

        // Create a PdfCompositeField instance, add the page number filed and the page
        // count field to composite filed
        PdfCompositeField compositeField = new PdfCompositeField(font, PdfBrushes.getBlack(), "Pág. {0} de {1}",
                pageNumberField, pageCountField);

        // Set string format for the composite field
        compositeField.setStringFormat(new PdfStringFormat(PdfTextAlignment.Left, PdfVerticalAlignment.Top));

        // Loop through the pages
        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            PdfPageBase page = pdf.getPages().get(i);
            float x = (float) page.getSize().getWidth() / 2 - 20;
            float y = (float) page.getSize().getHeight() - pdf.getPageSettings().getMargins().getBottom();
            // Draw the composite filed on each page
            compositeField.draw(page.getCanvas(), x, y);
        }

        // Save the result document
        pdf.saveToFile("C:\\Workspace\\autorizo\\src\\relatorios\\sample1.pdf");
    }
}