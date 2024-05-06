package controllers;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import static models.ConnectionGenerator.generateConnection;

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

        // Path to save uploaded file
        final String path = getServletContext().getRealPath(UPLOAD_DIRECTORY);
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        final String uploader = request.getParameter("uploader"); // Assuming uploader info is provided in the request

        // Validate file type
        if (!fileName.toLowerCase().endsWith(".pdf")) {
            response.getWriter().println("Only PDF files are allowed");
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

            // Save file information to the database
            saveFileDetails(fileName, file.getAbsolutePath(), uploader);

            response.getWriter().println("File " + fileName + " has been uploaded successfully!");
        } catch (Exception e) {
            response.getWriter().println("Error uploading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

private void saveFileDetails(String fileName, String filePath, String uploader) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO uploaded_files (file_name, file_path, uploader, upload_date) VALUES (?, ?, ?, CURDATE())"; // Make sure the table name matches your DB
    try (Connection conn = generateConnection(dbDriver, dbURL, user, pass);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, fileName);
        pstmt.setString(2, filePath);
        pstmt.setString(3, uploader);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception for better debugging
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
