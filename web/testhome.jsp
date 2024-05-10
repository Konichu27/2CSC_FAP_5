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
        <form action="files" method="POST">
        <% 
            String dbDriver = getServletContext().getInitParameter("driver_mysql");
            String dbURL = getServletContext().getInitParameter("url_mysql");
            String user = getServletContext().getInitParameter("username_mysql");
            String pass = getServletContext().getInitParameter("password_mysql");
            try {
                List<UploadedFile> uploadedFiles = FileDao.getUploadedFiles(dbDriver, dbURL, user, pass);
                if (uploadedFiles.isEmpty()) {
                    out.println("<li>No files available for download.</li>");
                } else {
                    for (UploadedFile file : uploadedFiles) {
                        String uploader = file.getUploader();
        %>
                        <button type="submit" id="uploader" name="uploader" class="btn" value="<%= uploader %>">view</button>
        <%
                    }
                }
            } catch (Exception e) {
                out.println("<li>Failed to load files. Please check the GlassFish server logs.</li>");
                e.printStackTrace();
            }
        %>
        </form>
    </ul>
</body>
</html>
