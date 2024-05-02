package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Edrine Frances
 * @author Leonne Matthew Dayao
 * @author Rayna Gulifardo
 * 2CSC - CICS - University of Santo Tomas
 */

public class LoginRequester
{
    private static Connection con;
    private static String key;
    private static String cipher;
    
    public LoginRequester(Connection con, String key, String cipher) {
        LoginRequester.con = con;
        LoginRequester.key = key;
        LoginRequester.cipher = cipher;
    }
    
    public Account loginRequest(String uname, String pword) throws NullValueException, AuthenticationException, ServerAuthenticationException
                {   
                    if (uname == null || uname.isEmpty()) {
                        // ERROR: both username and password is left blank, or username is blank while password is
                        // Controller catches NullValueException, redirects to noLoginCredentials.jsp
                        throw new NullValueException("6: User " + uname + " requested with a blank password.");
                    }
                    
                    boolean isPwordBlank = (pword == null || pword.isEmpty());
                    
                    String encrypPword;
                    encrypPword = Security.encrypt(pword, key, cipher);
                    encrypPword = Objects.toString(encrypPword, "");
                    
                    try (PreparedStatement psAcc = con.prepareStatement("SELECT * FROM USER_INFO WHERE username = ?"))
                    {
                        // Verify W/ Server
                        // DO NOT REMOVE THE NUMBERS. They are needed
                        psAcc.setString(1, uname);
                        try (ResultSet rsAcc = psAcc.executeQuery()) {
                            if (rsAcc.next()) // USERNAME CORRECT
                            { // next() method returns false if no corresp. entry is found.
                                if (rsAcc.getString("password").equals(encrypPword)) { // PASSWORD CORRECT
                                    return new Account(uname, encrypPword, rsAcc.getString("role"));
                                }
                                else if (isPwordBlank) {
                                    // ERROR 2: the username is correct but incorrect password
                                    // Controller detects empty password, redirects to error_2.jsp
                                    throw new AuthenticationException("5: User " + uname + " requested with a blank password.");
                                }
                                else {
                                    throw new AuthenticationException("2: User " + uname + " requested with an incorrect password.");
                                }
                            }
                            else if (isPwordBlank) {
                                // ERROR 3: the username and password are both incorrect, both are not blank
                                // Controller detects empty account, redirects to error_3.jsp
                                throw new AuthenticationException("1: User " + uname + " requested with an non-existing username and blank password.");
                            }
                            else {
                                throw new AuthenticationException("3: User " + uname + " requested with an non-existing username and password.");
                            }
                        }
                    } catch (SQLException sqle)
                    {
                        // ERROR: There was a problem with the server.
                        // Controller catches ServerAuthenticationException, redirects to error_login-generic.jsp
                        throw new ServerAuthenticationException("7: Connection with user " + uname + " has experienced a problem.");
                    }
            }
}