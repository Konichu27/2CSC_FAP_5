package controllers;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import static models.ConnectionGenerator.generateConnection;

@WebServlet(name = "FileServlet", urlPatterns =
{
    "/files"
})
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String dbDriver, dbURL, user, pass;
    
@Override
    public void init() throws ServletException {
        dbDriver = getServletContext().getInitParameter("driver_mysql");
        dbURL = getServletContext().getInitParameter("url_mysql"); // Updated database name
        user = getServletContext().getInitParameter("username_mysql");
        pass = getServletContext().getInitParameter("password_mysql");
    }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String fileUploader = request.getParameter("uploader");
    if (fileUploader == null || fileUploader.isEmpty()) {
        response.getWriter().println("Invalid file ID");
        return;
    }

    FileDetails fileDetails;
    try {
        fileDetails = getFileDetailsFromDatabase(fileUploader);
    } catch (ServletException | ClassNotFoundException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error accessing file details.");
        return;
    }

    if (fileDetails == null) {
        response.getWriter().println("File not found");
        return;
    }

        File downloadFile = new File(fileDetails.filePath);
        if (!downloadFile.exists()) {
            response.getWriter().println("File not found");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "Resume_" + fileDetails.fileOwner + ".pdf" + "\"");

        try (FileInputStream inputStream = new FileInputStream(downloadFile);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private FileDetails getFileDetailsFromDatabase(String username) throws ServletException, ClassNotFoundException {
    String sql = "SELECT first_name, last_name, resume_filepath FROM applicant WHERE email = ?";
    System.out.println("The username is " + username);
    try (Connection conn = generateConnection(dbDriver, dbURL, user, pass);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, username);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String fileOwner = rs.getString("first_name") + " " + rs.getString("last_name");
                String filePath = rs.getString("resume_filepath");
                if (rs.wasNull()) {
                    filePath = ""; // set it to empty string as you desire.
                    System.out.println("The file path is null!");
                }
                return new FileDetails(fileOwner, filePath);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Better error handling
        throw new ServletException("Database access error", e);
    }
    return null;
}



    // Helper class to hold file details
    private static class FileDetails {
        String fileOwner;
        String filePath;

        FileDetails(String fileUploader, String filePath) {
            this.fileOwner = fileUploader;
            this.filePath = filePath;
        }
    }
}
