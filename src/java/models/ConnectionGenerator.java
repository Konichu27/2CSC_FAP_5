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
    public static Connection generateConnection(String driver_mysql, String url_mysql, String username_mysql, String password_mysql) throws ClassNotFoundException, SQLException {
        // Explicitly loading the MySQL JDBC driver
            Class.forName(driver_mysql);
        System.out.println("Driver loaded successfully.");

        System.out.println("Connecting to database: " + url_mysql);
        Connection con = DriverManager.getConnection(url_mysql, username_mysql, password_mysql);
        System.out.println("Connection established.");
        return con;
    }
    
    public static Connection generateSQLConnection(String driver_mysql, String url_mysql, String username_mysql, String password_mysql) throws ClassNotFoundException, SQLException {
        // Explicitly loading the MySQL JDBC driver
            Class.forName(driver_mysql);
        System.out.println("Driver loaded successfully.");

        System.out.println("Connecting to database: " + url_mysql);
        Connection con = DriverManager.getConnection(url_mysql, username_mysql, password_mysql);
        System.out.println("Connection established.");
        return con;
    }
}
