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

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String fileId = request.getParameter("fileId");
    if (fileId == null || fileId.isEmpty()) {
        response.getWriter().println("Invalid file ID");
        return;
    }

    FileDetails fileDetails;
    try {
        fileDetails = getFileDetailsFromDatabase(fileId);
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
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileDetails.fileName + "\"");

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

private FileDetails getFileDetailsFromDatabase(String fileId) throws ServletException, ClassNotFoundException {
    String sql = "SELECT file_name, file_path FROM uploaded_files WHERE file_id = ?";
    try (Connection conn = generateConnection(dbDriver, dbURL, user, pass);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, Integer.parseInt(fileId));
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new FileDetails(rs.getString("file_name"), rs.getString("file_path"));
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
        String fileName;
        String filePath;

        FileDetails(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath;
        }
    }
}
