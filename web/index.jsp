<%-- 
    Document   : index.jsp
    Created on : 02 25, 24, 5:20:40 PM
    Author     : 2CSC Group 12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Sign In" />
        <link rel="shortcut icon" href="https://activelearning.ph/wp-content/uploads/2021/03/cropped-favicon-1-192x192.png"/>
        <style><%@include file="/WEB-INF/css/signtest.css"%></style>
        <title>Sign In Page</title>
    </head>
    <%  // Removes cache from index.jsp to prevent form resubmission
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Expires","0");
    response.setDateHeader("Expires",-1);
    %>
    <%      
    response.setContentType("text/html;charset=UTF-8");
    String uname, urole;
    try {
        uname = session.getAttribute("uname").toString();
        urole = session.getAttribute("urole").toString();
    }
    catch (NullPointerException npe) {
        uname = "";
        urole = "";
    }
    session.invalidate();
    %>
    <body>
        <jsp:include page="/WEB-INF/header.jsp"/>
        <div class="wrapper">
            <div id="content">
              <div class="entry-header">
                <h1 class="entry-title">Sign In</h1>
                <p>Enter your credentials below</p>
              </div>

              <div id="login-form-container">
                <div class="forms">
                  <form class="form" action="login" method="POST">
                    <div class="username">
                      <input id="uname" name="uname" type="text" placeholder="Username*" required
                        autocomplete="off">
                    </div>
                    <div class="password">
                      <input id="pword" name="pword" type="password" placeholder="Password*" required
                        autocomplete="off">
                    </div>
                    <div class="button">
                      <button type="submit" id="submit" class="btn">Log In</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>    
        <%-- <jsp:include page="/WEB-INF/footer.jsp"/> --%>
    </body>
</html>