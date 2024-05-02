package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dayao, Leonne Matthew H. // UST - 1CSC
 */
public class ConnectionGenerator
{
    public static Connection generateConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        System.out.println("Loaded Driver: " + driver);
        // Establish Connection
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to: " + url);
        return con;
    }
}
