package models;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelfReport {
    public static boolean generateReport(OutputStream os, Connection con, String key, String cipher, String username) throws PdfGenerationException {
        Document document = new Document(new Rectangle(360, 216));
        try {
            
            // Retrieve passwordEntry
            String password = "";
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USER_INFO WHERE username = ?");
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    password = rs.getString("password").trim();
                }
            }
            
            PdfWriter writer = PdfWriter.getInstance(document, os);

            document.open();

            Font reportTypeFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
            Paragraph reportType = new Paragraph("User Record", reportTypeFont);
            reportType.setAlignment(Element.ALIGN_CENTER);
            document.add(reportType);

            // Add a space
            document.add(new Paragraph(" "));
            
            // Decrypt passwordEntry
            String truePassword = Security.decrypt(password, key, cipher);

            // user and role
            Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            Paragraph usernameEntry = new Paragraph("Username: " + username, contentFont);
            usernameEntry.setAlignment(Element.ALIGN_CENTER);
            Paragraph passwordEntry = new Paragraph("Password: " + truePassword, contentFont);
            passwordEntry.setAlignment(Element.ALIGN_CENTER);
            document.add(usernameEntry);
            document.add(passwordEntry);

            // footer first page
            PdfContentByte cb = writer.getDirectContent();
            Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN,10, Font.ITALIC);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - h:mm:ss a"); 
            String timestamp = LocalDateTime.now().format(formatter); 
            
            // header
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("Created " + timestamp, footerFont),
                                        document.getPageSize().getWidth() / 2, document.top() + 16, 0);

            // footer
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                    new Phrase(String.format("Page %d of %d", writer.getPageNumber(), writer.getPageNumber()), footerFont),
                    document.right() - 2, document.bottom() - 20, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    new Phrase(username, footerFont),
                    document.left() + 2, document.bottom() - 20, 0);
            document.close();
            return true;
        } catch (DocumentException | SQLException e) {
            throw new PdfGenerationException("PDF generation failed. Please check server logs.");
        }
    }
}