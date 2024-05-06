<%@page import="java.util.List"%>
<%@page import="models.UploadedFile"%>
<%@page import="models.FileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File Upload and Download</title>
</head>
<body>
    <h2>Upload a File</h2>
    <form action="uploads" method="post" enctype="multipart/form-data">
        <input type="file" name="file" required>
        <input type="submit" value="Upload">
    </form>

    <hr>

    <h2>Download Uploaded Files</h2>
    <ul>
        <% 
            try {
                String dbDriver = getServletContext().getInitParameter("driver_mysql");
                String dbURL = getServletContext().getInitParameter("url_mysql"); // Updated database name
                String user = getServletContext().getInitParameter("username_mysql");
                String pass = getServletContext().getInitParameter("password_mysql");
                List<UploadedFile> uploadedFiles = FileDao.getUploadedFiles(dbDriver, dbURL, user, pass);
                for (UploadedFile file : uploadedFiles) {
                String fileName = file.getFileName();
                int fileId = file.getFileId();                                       %>
                <li><a href="files?fileId=<%= fileId %>"><%= fileName %></a></li>
            <%  }
            }
            catch (Exception e) {
                e.printStackTrace(); %>
                Failed to load. Please check the GlassFish server logs.
            <% }
        %>
    </ul>
</body>
</html>
