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
        String sql = "SELECT resume_filepath, email FROM applicant";
        
        try (Connection conn = generateConnection(dbDriver,dbURL, user, pass);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String filePath = rs.getString("resume_filepath");
                if (rs.wasNull()) {
                    filePath = ""; // set it to empty string as you desire.
                }
                files.add(new UploadedFile(rs.getString("email"), filePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error accessing database", e);
        }
        return files;
    }
}
