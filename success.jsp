<%-- 
    Document   : successtest
    Created on : 02 23, 24, 2:16:05 PM
    Author     : Dayao, Leonne Matthew H. // UST - 1CSC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%  // Removes cache from success.jsp to prevent form resubmission
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Expires","0");
    response.setDateHeader("Expires",-1);
    %>
    <body>
        <h1>Welcome ${sessionScope.urole} ${sessionScope.uname}</h1>
            <form action="logout" method="POST">
                <input <%-- class="btn" --%> type="submit" id="submit" value="Logout">
            </form>
    </body>
</html>
