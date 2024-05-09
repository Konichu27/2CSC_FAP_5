package models;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FullReport {
    public static void generateReport(OutputStream os, Connection con, String username) throws PdfGenerationException {
        Document document = new Document(PageSize.LETTER.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            HeaderAndFooter event = new HeaderAndFooter("USER_INFO Records", username);
            writer.setPageEvent(event);

            document.open();

            /* Header */
            Font reportTypeFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
            Paragraph reportType = new Paragraph("Admin Report", reportTypeFont);
            reportType.setAlignment(Element.ALIGN_CENTER);
            document.add(reportType);

            /* Table Settings */
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f); 
            table.setSpacingAfter(10f); 

            float[] columnWidths = {1f,1f, 1f};
            table.setWidths(columnWidths);
            
            /* Table Header */
            Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            table.addCell(new Phrase("#", tableHeaderFont));
            table.addCell(new Phrase("User", tableHeaderFont));
            table.addCell(new Phrase("Role", tableHeaderFont));
            
            /* Table Contents */
            PreparedStatement ps = con.prepareStatement("SELECT * FROM APP.USER_INFO");
            try (ResultSet rs = ps.executeQuery())
            {
                int i = 1;
                while (rs.next())
                {
                    table.addCell(i++ + "");
                    // Font tableUsernameFont = new Font(Font.FontFamily.HELVETICA, 12); 
                    // Paragraph tableUsername = new Paragraph(rs.getString("username").trim(), reportTypeFont);
                    String tableUsername = rs.getString("username").trim();
                    if (tableUsername.equals(username)) {
                        tableUsername += "*";
                    }
                    table.addCell(new Phrase(tableUsername));
                    
                    table.addCell(rs.getString("role").trim());
                }
            }
            document.add(table);

            while ((writer.getPageNumber() % 2) != 0) {
                document.newPage();
            }
            document.close();
        } catch (DocumentException | SQLException e) {
            throw new PdfGenerationException("PDF generation failed. Please check server logs.");
        }
    }

    /*
     * Header and Footer Page Event Helper Class
     */
    public static class HeaderAndFooter extends PdfPageEventHelper {
        private String name, username;
        private static Font footerFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);

        public HeaderAndFooter(String role, String username) {
            this.name = role;
            this.username = username;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            String footerContent = "Page " + writer.getPageNumber() + " of 2";
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - h:mm:ss a"); 
            String timestamp = LocalDateTime.now().format(formatter); 
            
            /* Header */
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Created " + timestamp, footerFont),
                                        document.left() + 2, document.top() - 20, 0);
            
            /* Footer */
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(footerContent, footerFont),
                                        document.right() - 2, document.bottom() - 20, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(username, footerFont),
                                        document.left() + 2, document.bottom() - 20, 0);
        }
    }
}
