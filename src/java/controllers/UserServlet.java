package controllers;

import static controllers.CaptchaServlet.generateCaptcha;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "UserServlet", urlPatterns =
{
    "/admin/accounts"
})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // PrintWriter out = response.getWriter();
        
        final String errorOutput = "There was error in rendering the table. Please refresh.";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String tableOutput = "";

        try {
            String driver = getServletContext().getInitParameter("driver");
            String url = getServletContext().getInitParameter("url"); // change to UserDB once ready
            String conUsername = getServletContext().getInitParameter("username");
            String conPassword = getServletContext().getInitParameter("password");
            
            con = generateConnection(driver, url, conUsername, conPassword);
            String sql = "SELECT USERNAME, ROLE FROM USER_INFO";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("USERNAME");
                String role = rs.getString("ROLE");
                tableOutput += "<tr><td>" + username + "</td><td>" + role + "</td></tr>";
                // out.print("<tr><td>" + username + "</td><td>" + role + "</td></tr>");
            }
            request.setAttribute("table", tableOutput);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("table", errorOutput);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("table", errorOutput);
            }
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/admin/AccountsPage.jsp");
        rd.forward(request, response);
    }
}
