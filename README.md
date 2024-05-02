

DB will have 3 columns (UserDB, table name: USER_INFO)
 - username
 - password
 - role (guest / admin)
 - provide 20 records, 5 admin and 15 guest roles

Landing Page (login Page - index.jsp)
 - Header 
 - Username and password and a submit button
 - Footer

Success Page (success.jsp)
 - Header 
 - A welcome message stating the user's name and the user's role
 - Logout Button, once clicked must destroy the session and will land back to the login page.
 - Once logout button (or link) was clicked, destroy the session and once the back button in the browser was clicked you should NOT be allowed to go back to the success page.
 - Take note, you have to use Session Management.
 - Footer

Error Page (error_1.jsp, error_2.jsp, error_3.jsp, etc)
 - Header 
 - Error message, why you end up here.
 - link going back to the login page / previous page
 - Footer

SessionDestroyed Error Page (error_session.jsp)
 - Header 
 - Error message once you attempt to go to the successpage without properly logging in.
 - Footer

Login Servlet
 - use the public void init(ServletConfig) {} method to load the DB credentials at start up (DB Name, db username, db password and URL)
 - If the login succeeds, create a session object and proceed to the success page.
 - If the login is unsuccessful, go to the right error page.
 - error_1.jsp (if the username is not in the DB, and password is blank)
 - error_2.jsp (if the username is correct but incorrect password)
 - error_3.jsp (if the username and password are both incorrect, both are not blank) 
 ----> if both username and password is left blank, throw a NullValueException to 
 call another error page, ie: noLoginCredentials.jsp.
 - error_4.jsp (if an error 404 status code is encountered)

Must Have's:
 ServletConfig in your DD where you keep the following:
  - DB username, DB password, DB driver and URL
  - ServletContext in your DD where you keep the value for the header and footer
  - Session Timeout value set to 5 minutes
  - welcome file set to index.jsp

Additional Pages:
 - Error page declarations. Give at least 5.
 - AuthenticationException for incorrect username / password
 - NullValueException for no username / password
 - Error 404, for incorrect context path

Your Java Web App Project should be: ICS2609_SECTION_LASTNAME1_LASTNAME2_LASTNAME3.

Deadline:
 - Mar 1, 1pm
