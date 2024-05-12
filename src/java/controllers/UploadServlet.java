package controllers;

import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import static models.ConnectionGenerator.generateConnection;
import models.InsertionException;

@WebServlet(name = "UploadServlet", urlPatterns =
{
    "/uploads"
})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "/uploads";
    private static String dbDriver, dbURL, user, pass;
    
@Override
    public void init() throws ServletException {
        dbDriver = getServletContext().getInitParameter("driver_mysql");
        dbURL = getServletContext().getInitParameter("url_mysql"); // Updated database name
        user = getServletContext().getInitParameter("username_mysql");
        pass = getServletContext().getInitParameter("password_mysql");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final String errorOutput = "There was error in insertion. Please go back and try applying again";
        
        Connection con = null;
        PreparedStatement stmt = null;
        String email = "", salutations = "", firstName = "", lastName = "", appRole = "", mobileNumber = "";
        
        try {
            con = generateConnection(dbDriver, dbURL, user, pass);
            String sql = "INSERT INTO applicant"
                    + "(email, salutations, first_name, last_name,"
                    + "app_role, mobile_number, archive) VALUES "
                    + " (?, ?, ?, ?, ?, ?, 0)";
            stmt = con.prepareStatement(sql);
            
            email = request.getSession().getAttribute("uname").toString();
            salutations = request.getParameter("salutations");
            firstName = request.getParameter("first_name");
            lastName = request.getParameter("last_name");
            appRole = request.getParameter("app_role");
            mobileNumber = request.getParameter("mobile_number");
            
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
            return;
          }
           catch (IllegalStateException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error_message", errorOutput);
            response.sendRedirect("error.jsp");
            return;
          }
          catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error_message", errorOutput);
            response.sendRedirect("error.jsp");
            return;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Path to save uploaded file
        final String path = getServletContext().getRealPath(UPLOAD_DIRECTORY)+"/";
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        // Validate file type
        if (!fileName.toLowerCase().endsWith(".pdf")) {
            request.getSession().setAttribute("error_message", "Only PDF files are allowed.");
            response.sendRedirect("error.jsp");
            return;
        }

        File uploadsDir = new File(path);
        if (!uploadsDir.exists()) uploadsDir.mkdirs();

        File file = new File(uploadsDir, fileName);

        try (InputStream filecontent = filePart.getInputStream();
            FileOutputStream out = new FileOutputStream(file)) {
            int read;
            final byte[] bytes = new byte[1024];  // Adjust size if necessary
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            String sql = "UPDATE applicant set resume_filepath = ? WHERE email = ?"; // Make sure the table name matches your DB
            try (Connection conn = generateConnection(dbDriver, dbURL, user, pass);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, file.getAbsolutePath());
                pstmt.setString(2, email);
                System.out.println("Email is " + email);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                request.getSession().setAttribute("error_message", errorOutput);
                response.sendRedirect("error.jsp");
                return;
            }

            response.sendRedirect("/2CSC_FAP_5/checkGuest");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error_message", errorOutput);
            response.sendRedirect("error.jsp");
            return;
        }
    }


    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
