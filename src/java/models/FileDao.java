package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static models.ConnectionGenerator.generateConnection;

public class FileDao {
    // NOTE: For prototyping purposes, SSL is disabled.
    // The database won't load otherwise due to certificate settings. To fix this, you'll have to do some edits in GlassFish SERVER.
    // DISABLING SSL IS ABSOLUTELY NOT NOT RECOMMENDED IN REAL-WORLD USE.
    
    // private static final String DB_URL = "jdbc:mysql://localhost:3310/file"; // Updated database name

    public static List<UploadedFile> getUploadedFiles(String dbDriver, String dbURL, String user, String pass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<UploadedFile> files = new ArrayList<>();
        String sql = "SELECT file_id, file_name FROM uploaded_files";
        
        try (Connection conn = generateConnection(dbDriver, dbURL, user, pass);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                files.add(new UploadedFile(rs.getInt("file_id"), rs.getString("file_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error accessing database", e);
        }
        return files;
    }
}
