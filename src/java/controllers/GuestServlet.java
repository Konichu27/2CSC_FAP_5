package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(name = "GuestServlet", urlPatterns = {"/checkGuest"})
public class GuestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String ERROR_MESSAGE_GENERIC = "There was a problem loading the page.";
    private final String ERROR_MESSAGE_DB = "Database connection error. Please try again.";
    private final String ERROR_MESSAGE_LOGIN = "You must have be logged in as an admin to access this page.";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean isGuest = false;
        try
        {
            // gets user role from login session
            isGuest = request.getSession().getAttribute("urole").toString().equals("Guest");
        } catch (NullPointerException e)
        {
            isGuest = false;
        }
        finally {
            if (!isGuest)
            {
                request.getSession().setAttribute("error_message", ERROR_MESSAGE_LOGIN);
                response.sendRedirect("error.jsp");
            }
        }
        // String path = "/WEB-INF/guest/guest.jsp"; // Default redirect
        boolean isSubmitted = false;

        try {
            String dbDriver = getServletContext().getInitParameter("driver_mysql");
            String dbURL = getServletContext().getInitParameter("url_mysql");
            String user = getServletContext().getInitParameter("username_mysql");
            String pass = getServletContext().getInitParameter("password_mysql");
            
            try (Connection con = generateConnection(dbDriver, dbURL, user, pass)) {
                String sql = "SELECT * FROM applicant WHERE email = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, request.getSession().getAttribute("uname").toString());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        // set attributes
                        request.setAttribute("salutations", rs.getString("salutations").trim());
                        request.setAttribute("first_name", rs.getString("first_name").trim());
                        request.setAttribute("last_name", rs.getString("last_name").trim());
                        request.setAttribute("mobile_number", rs.getString("mobile_number").trim());
                        request.setAttribute("email", rs.getString("email").trim());
                        request.setAttribute("app_role", rs.getString("app_role").trim());
                        isSubmitted = true;
                    }
                }
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();  // Better error handling
            request.getSession().setAttribute("error_message", ERROR_MESSAGE_DB);
            response.reset(); // Removing this gives an IllegalStateException
            response.sendRedirect("error.jsp");
            }
        catch (NullPointerException e) {
            e.printStackTrace();  // Better error handling
            request.getSession().setAttribute("error_message", ERROR_MESSAGE_GENERIC);
            response.reset(); // Removing this gives an IllegalStateException
            response.sendRedirect("error.jsp");
        }
        
        String path = "";
        if (isSubmitted) {
            path = "/WEB-INF/guest/guestsubmitted.jsp";
        }
        else path = "/WEB-INF/guest/guest.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
