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

@WebServlet(name = "ApplicantServlet", urlPatterns =
{
    "/admin/applicants"
})
public class ApplicantServlet extends HttpServlet {
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
            String driver = getServletContext().getInitParameter("driver_mysql");
            String url = getServletContext().getInitParameter("url_mysql"); // change to UserDB once ready
            String conUsername = getServletContext().getInitParameter("username_mysql");
            String conPassword = getServletContext().getInitParameter("password_mysql");
            
            con = generateConnection(driver, url, conUsername, conPassword);
            String sql = "SELECT * FROM applicant";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String email = rs.getString("email");
                String salutations = rs.getString("salutations");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String appRole = rs.getString("app_role");
                String mobileNumber = rs.getString("mobile_number");
                tableOutput += "<tr><td>" + "<input type=\"checkbox\">"
                        + "</td><td>" + salutations
                        + "</td><td>" + firstName
                        + "</td><td>" + lastName
                        + "</td><td>" + appRole
                        + "</td><td>" + email
                        + "</td><td>" + mobileNumber
                        + "</td><td>" + "<button type=\"submit\" id=\"uploader\""
                        + "name=\"uploader\" class=\"btn\" value=\""
                        + email
                        + "\">view</button>"// resume
                        + "</td></tr>";
                request.setAttribute("table", tableOutput);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("table", errorOutput);
            System.out.println("Error moment 1");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("table", errorOutput);
                System.out.println("Error moment 1");
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/admin/Applicants.jsp");
        rd.forward(request, response);
    }
}
