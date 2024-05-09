<%-- 
    Document   : captcha.jsp
    Created on : 04 8, 24, 2:16:57 PM
    Author     : 2CSC Group 12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Captcha" />
        <title>CAPTCHA</title>
        <style><%@include file="/WEB-INF/css/captcha.css"%></style>
        <script>
            // para hindi macopy
            function preventCopy(event) {
                event.preventDefault();
            }
        </script>
    </head>
    <%  // Removes cache from success.jsp to prevent form resubmission
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache"); 
        response.setHeader("Expires","0");
        response.setDateHeader("Expires",-1);
    %>
    <%      
    response.setContentType("text/html;charset=UTF-8");
    String isCaptchaValid;
    try {
        isCaptchaValid = session.getAttribute("isCaptchaValid").toString();
        }
    catch (Exception e) {
        isCaptchaValid = "";
        }
    if (isCaptchaValid.equals("true")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/captcha"));
            session.invalidate();
            return;
        }
    %>
    <body>
        <input type="hidden" id="refreshed" value="no">
        <script type="text/javascript">
               onload=function(){
                   var e=document.getElementById("refreshed");
                   if(e.value=="no")e.value="yes";
                   else{e.value="no";location.reload();}
               }
        </script>
        
        <jsp:include page="/WEB-INF/header.jsp"/> 
        <div class="wrapper">
            <div id="content">
              <div class="entry-header">
                <h1 class="entry-title">Captcha Verification</h1>
              </div>
              <p>Generated Captcha:</p>
              <h2 id="captcha" oncopy="preventCopy(event)">${sessionScope.captcha}</h2>
              
              <div class="forms">
                <form class="form" action="captchaVerif" method="POST">
                  <input id="captchaVerif" name="captchaVerif" type="captcha" placeholder="Enter Captcha*" required autocomplete="off">
                  <br>
                  <button type="submit" id="submit" class="btn">Submit</button>
                </form>
              </div>
            </div>
        </div>
        <%--<jsp:include page="/WEB-INF/footer.jsp"/> --%>
    </body>
</html>
