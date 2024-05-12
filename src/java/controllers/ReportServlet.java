package controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.FullReport;
import models.ConnectionGenerator;
import models.SelfReport;
import models.InvalidRequestException;
import models.PdfGenerationException;
import models.Security;

/**
 *
 * @author Dayao, Leonne Matthew H. // UST - 1CSC
 */
@WebServlet(name = "ReportServlet", urlPatterns =
{
    "/downloadReport"
})
public class ReportServlet extends HttpServlet
{
    
    private void generateUserInfoReport(OutputStream os, Connection con, String username) throws PdfGenerationException {
        Document document = new Document(PageSize.LETTER.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            HeaderAndFooter event = new HeaderAndFooter("User Info Records", username);
            writer.setPageEvent(event);

            document.setMargins(36f, 36f, 54f, 36f);
            document.open();

            /* Margins */

            /* Table Settings */
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100); 
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
                    // Paragraph tableUsername = new Paragraph(rs.getString("bottomCenter").trim(), reportTypeFont);
                    String tableUsername = rs.getString("username").trim();
                    if (tableUsername.equals(username)) {
                        tableUsername += "*";
                    }
                    table.addCell(new Phrase(tableUsername));
                    table.addCell(rs.getString("role").trim());
                }
            }
            document.add(table);

            /*while ((writer.getPageNumber() % 2) != 0) {
                document.setMargins(document.leftMargin(),
                                    document.rightMargin(),
                                    document.topMargin() + 10f,
                                    document.bottomMargin());
                document.newPage();
            }*/
            document.close();
        } catch (DocumentException | SQLException e) {
            e.printStackTrace();
            throw new PdfGenerationException("PDF generation failed. Please check server logs.");
        }
    }
    
    private void generateSelfReport(OutputStream os, Connection con, String key, String cipher, String username) throws PdfGenerationException {
        Document document = new Document(new Rectangle(360, 216));
        try {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            
            // Retrieve passwordEntry
            String password = "";
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USER_INFO WHERE username = ?");
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    password = rs.getString("password").trim();
                    System.out.println(password);
                }
            }

            document.setMargins(36f, 36f, 54f, 36f);
            document.open();
            
            // Add spaces
            document.add(new Paragraph(" "));
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
            
            HeaderAndFooter event = new HeaderAndFooter("Self Record", username, true);
            writer.setPageEvent(event);
            
            document.close();
        } catch (DocumentException | SQLException e) {
            e.printStackTrace();
            throw new PdfGenerationException("PDF generation failed. Please check server logs.");
        }
    }

    /**
     * 
     * @param os Output stream to write PDF to
     * @param con Database connection to reference
     * @param username Username for footer
     * @param isArchived 'false' checks current, 'true' checks archives
     * @throws PdfGenerationException 
     */
    private void generateApplicantReport(OutputStream os, Connection con, String username, boolean isArchived) throws PdfGenerationException {
        Document document = new Document(PageSize.LETTER.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            
            // Set String title to use. (ternary operator)
            // if archived, then output Archived Applicants
            // else, output Current Applicants
            
            String title = (isArchived) ? ("Archived Applicants") : ("Current Applicants");
            HeaderAndFooter event = new HeaderAndFooter(title, username);
            writer.setPageEvent(event);

            document.setMargins(36f, 36f, 54f, 36f);
            document.open();

            /* Margins */

            /* Table Settings */
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100); 
            table.setSpacingAfter(10f); 

            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            
            /* Table Header */
            Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            table.addCell(new Phrase("Salutations", tableHeaderFont));
            table.addCell(new Phrase("First Name", tableHeaderFont));
            table.addCell(new Phrase("Last Name", tableHeaderFont));
            table.addCell(new Phrase("Applied Role", tableHeaderFont));
            table.addCell(new Phrase("Email", tableHeaderFont));
            table.addCell(new Phrase("Mobile #", tableHeaderFont));
            
            /* Table Contents */
            PreparedStatement ps = con.prepareStatement("SELECT * FROM file.applicant ORDER BY last_name");
            // if archived, look for values marked 1 in the table
            // else, look for 0 (current)
            // int tinyIntValue = (isArchived) ? (1) : (0);
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next()) {
                        table.addCell(rs.getString("salutations").trim());
                        table.addCell(rs.getString("first_name").trim());
                        table.addCell(rs.getString("last_name").trim());
                        table.addCell(rs.getString("app_role").trim());
                        table.addCell(rs.getString("email").trim());
                        table.addCell(rs.getString("mobile_number"));
                }
            }
            document.add(table);

            /*while ((writer.getPageNumber() % 2) != 0) {
                document.setMargins(document.leftMargin(),
                                    document.rightMargin(),
                                    document.topMargin() + 10f,
                                    document.bottomMargin());
                document.newPage();
            }*/
            document.close();
        } catch (DocumentException | SQLException e) {
            e.printStackTrace();
            throw new PdfGenerationException("PDF generation failed. Please check server logs.");
        }
    }
    
    /*
     * Header and Footer Page Event Helper Class
     */
    
    /**
     * Header & footer generation.
     *
     * @param title Title of the document
     * @param username Username of the requester
     * @param isSmall Outputs a different, appropriately-sized header & footer if the document is set as small
     */
    private class HeaderAndFooter extends PdfPageEventHelper {
        private String title, username;
        private boolean isSmall = false;
        private Font headerFooterFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
        private Font webDomainFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        private Font reportTypeFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);

        public HeaderAndFooter(String title, String username) {
            this.title = title;
            this.username = username;
        }
        
        public HeaderAndFooter(String title, String username, boolean isSmall) {
            this.title = title;
            this.username = username;
            this.isSmall = isSmall;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            String footerContent = "Page " + writer.getPageNumber() + " of 2";
            
            if (isSmall) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  h:mm:ss a"); 
                String timestamp = LocalDateTime.now().format(formatter); 
                footerContent = "Page " + writer.getPageNumber() + " of 1";

                /* Header */

                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(title, reportTypeFont),
                                            (document.left() + document.right()) / 2f, document.top() - 35, 0);

                ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(getServletContext().getInitParameter("Name")
                        + " - "
                        + getServletContext().getInitParameter("Section"), headerFooterFont),
                                            document.right() - 2, document.top() + 20, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(timestamp, headerFooterFont),
                                            document.left() + 2, document.top() + 20, 0);

                /* Footer */
                ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(footerContent, headerFooterFont),
                                            document.right() - 2, document.bottom() - 20, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                                            "ActiveLearning Inc. | " + 
                                            getServletContext().getInitParameter("Domain"), webDomainFont),
                                            document.left() + 2, document.bottom() - 20, 0);
            }
            
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - h:mm:ss a"); 
                String timestamp = LocalDateTime.now().format(formatter); 

                /* Header */

                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(title, reportTypeFont),
                                            (document.left() + document.right()) / 2f, document.top() + 10, 0);

                ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(getServletContext().getInitParameter("Name"), headerFooterFont),
                                            document.right() - 2, document.top() + 20, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Created " + timestamp, headerFooterFont),
                                            document.left() + 2, document.top() + 20, 0);

                /* Footer */
                ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(footerContent, headerFooterFont),
                                            document.right() - 2, document.bottom() - 20, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(
                                            "ActiveLearning Inc. | " + 
                                            getServletContext().getInitParameter("Domain"), webDomainFont),
                                            (document.left() + document.right()) / 2, document.bottom() - 20, 0);
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(username, headerFooterFont),
                                            document.left() + 2, document.bottom() - 20, 0);
            }
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String uname, urole, isCaptchaValid;
        HttpSession session = request.getSession();
        try {
            uname = session.getAttribute("uname").toString();
            urole = session.getAttribute("urole").toString();
            isCaptchaValid = session.getAttribute("isCaptchaValid").toString();
        }
        catch (NullPointerException npe) {
            uname = "";
            urole = "";
            isCaptchaValid = "";
        }
        if (!uname.isEmpty() && !urole.isEmpty() && isCaptchaValid.equals("true")) {
            
            
            /*
                yyyy - 2024 - year
                  MM - 05 - May
                  dd - 01 - May 1
                  HH - 08 - 8am
                  mm - 10 - 8:10am
                  ss - 12 - 8:10:12am
            */
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); 
            String timestamp = LocalDateTime.now().format(formatter); 
            
            response.setContentType("application/force-download");
            
            // COURSELIST_20240501081012
            // yyyyMMddHHmmss
            response.setHeader("Content-Disposition","inline;filename="
                    + uname + "_"
                    + timestamp
                    + ".pdf");
            
            String requestType = request.getParameter("reportType");
            
            
            // Checks which database to use.
            // If full_rec or self_rec, refers to Derby settings.
            // If archived_app or current_app, refers to MySQL settings.
            // VERY wordy code but this is rushed soooooo
            String driver = "", url = "", username = "", password = "";
            if (requestType.equals("full_rec") || requestType.equals("self_rec")) {
                driver = getServletContext().getInitParameter("driver");
                url = getServletContext().getInitParameter("url"); // change to UserDB once ready
                username = getServletContext().getInitParameter("username");
                password = getServletContext().getInitParameter("password");
            }
            else if (requestType.equals("archived_app") || requestType.equals("current_app")) {
                driver = getServletContext().getInitParameter("driver_mysql");
                url = getServletContext().getInitParameter("url_mysql"); // change to UserDB once ready
                username = getServletContext().getInitParameter("username_mysql");
                password = getServletContext().getInitParameter("password_mysql");
            }
            
            String key = getServletContext().getInitParameter("key");
            String cipher = getServletContext().getInitParameter("cipher");
            String sessionUname = session.getAttribute("uname").toString();
            
            try (Connection con = ConnectionGenerator.generateConnection(driver, url, username, password)) {
                ServletOutputStream sos = response.getOutputStream();
                System.out.println("Report type: " + request.getParameter("reportType"));
                switch (request.getParameter("reportType")) {
                    case "full_rec": // Option to print all records
                        generateUserInfoReport(sos, con, sessionUname);
                        break;
                    case "self_rec": // Option to print only their records
                        generateSelfReport(sos, con, key, cipher, sessionUname);
                        break;
                    case "archived_app": // True means print out archived applicants
                        generateApplicantReport(sos, con, sessionUname, true);
                        break;
                    case "current_app": // False means print out current applicants
                        generateApplicantReport(sos, con, sessionUname, false);
                        break;
                    default:
                        sos.flush();
                        sos.close();
                        throw new InvalidRequestException("The request to the server did not have the required attributes for it.");
                }
                sos.flush();
                sos.close();
                // connects to success.jsp, see form sending to downloadReport
            }
            catch (Exception e) { 
                e.printStackTrace();
                String error = "There was a problem with downloading the PDF file.\nPlease try again.";
                request.getSession().setAttribute("error_message", error);
                RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
                response.reset();
                rs.forward(request, response);
            }
        }
        else {
            String error = "You cannot access this page directly.";
            request.getSession().setAttribute("error_message", error);
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
