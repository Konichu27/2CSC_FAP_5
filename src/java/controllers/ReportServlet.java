package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import models.AdminReport;
import models.ConnectionGenerator;
import models.GuestReport;

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
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"); 
            String timestamp = LocalDateTime.now().format(formatter); 
            
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition","inline;filename="
                    + uname + "_"
                    + timestamp
                    + ".pdf");
            
            String driver = getServletContext().getInitParameter("driver");
            String url = getServletContext().getInitParameter("url"); // change to UserDB once ready
            String username = getServletContext().getInitParameter("username");
            String password = getServletContext().getInitParameter("password");
            
            String key = getServletContext().getInitParameter("key");
            String cipher = getServletContext().getInitParameter("cipher");
            String sessionUname = session.getAttribute("uname").toString();
            
            try (Connection con = ConnectionGenerator.generateConnection(driver, url, username, password)) {
                ServletOutputStream sos = response.getOutputStream();
                switch (session.getAttribute("urole").toString()) {
                    case "Guest":
                        System.out.println(GuestReport.generateReport(sos, con, key, cipher, sessionUname));
                        break;
                    case "Admin":
                        System.out.println(AdminReport.generateReport(sos, con, sessionUname));
                        break;
                }
                sos.flush();
                sos.close();
            }
            catch (Exception e) {
                e.printStackTrace(); // fix this eventually
            }
        }
        else {
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("error_session.jsp");
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
