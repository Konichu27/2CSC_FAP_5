package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static models.ConnectionGenerator.generateConnection;
import models.InsertionException;

@WebServlet(name = "InsertionServlet", urlPatterns = {"/submit"})
public class InsertionServlet extends HttpServlet
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
        response.setContentType("text/html;charset=UTF-8");
        
        final String errorOutput = "There was error in insertion. Please go back and try applying again";
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            String driver = getServletContext().getInitParameter("driver_mysql");
            String url = getServletContext().getInitParameter("url_mysql"); // change to UserDB once ready
            String conUsername = getServletContext().getInitParameter("username_mysql");
            String conPassword = getServletContext().getInitParameter("password_mysql");
            
            con = generateConnection(driver, url, conUsername, conPassword);
            String sql = "INSERT INTO applicant"
                    + "(email, salutations, first_name, last_name,"
                    + "app_role, mobile_number, archive) VALUES "
                    + " (?, ?, ?, ?, ?, ?, 0)";
            stmt = con.prepareStatement(sql);
            
            String email = request.getSession().getAttribute("uname").toString();
            String salutations = request.getParameter("salutations");
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String appRole = request.getParameter("app_role");
            String mobileNumber = request.getParameter("mobile_number");
            
            stmt.setString(1, email);
            stmt.setString(2, salutations);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, appRole);
            stmt.setString(6, mobileNumber);
            
            int checkValue = stmt.executeUpdate();
            if (checkValue < 1) {
                throw new InsertionException(errorOutput);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error_message", errorOutput);
            response.sendRedirect("error.jsp");
          }
           catch (IllegalStateException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error_message", errorOutput);
            response.sendRedirect("error.jsp");
          }
          catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error_message", errorOutput);
            response.sendRedirect("error.jsp");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/2CSC_FAP_5/checkGuest");
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
