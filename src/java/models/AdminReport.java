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

public class AdminReport {
    public static boolean generateReport(OutputStream os, Connection con, String username) {
        Document document = new Document(PageSize.LETTER);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            HeaderAndFooter event = new HeaderAndFooter("Administrator", username);
            writer.setPageEvent(event);

            document.open();

            Font reportTypeFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
            Paragraph reportType = new Paragraph("Admin Report", reportTypeFont);
            reportType.setAlignment(Element.ALIGN_CENTER);
            document.add(reportType);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f); 
            table.setSpacingAfter(10f); 

            float[] columnWidths = {1f,1f, 1f};
            table.setWidths(columnWidths);
            
            Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            table.addCell(new Phrase("#", tableHeaderFont));
            table.addCell(new Phrase("User", tableHeaderFont));
            table.addCell(new Phrase("Role", tableHeaderFont));
            
            // Retrieve passwordEntry
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USER_INFO");
            try (ResultSet rs = ps.executeQuery())
            {
                int i = 1;
                while (rs.next())
                {
                    table.addCell(i++ + "");
                    table.addCell(rs.getString("username").trim());
                    table.addCell(rs.getString("role").trim());
                }
            }
            document.add(table);

            while ((writer.getPageNumber() % 2) != 0) {
                document.newPage();
            }
            document.close();
            return true;
        } catch (DocumentException | SQLException e) {
            e.printStackTrace();
            return false;
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
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(footerContent, footerFont),
                                        document.right() - 2, document.bottom() - 20, 0);
            
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(username, footerFont),
                                        document.left() + 2, document.bottom() - 20, 0);
        }
    }
}
