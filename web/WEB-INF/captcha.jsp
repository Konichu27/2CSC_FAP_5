<%-- 
    Document   : captcha.jsp
    Created on : 04 8, 24, 2:16:57 PM
    Author     : Dayao, Leonne Matthew H. // UST - 1CSC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Captcha Generator</title>
        <script>
            // para hindi macopy
            function preventCopy(event) {
                event.preventDefault();
            }
        </script>
        <style>
        html {
            height: 100%;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(#30142b, #2772a1);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
        }

        h1 {
            color: white;
            font-family: 'Poppins', sans-serif;
            text-align: center;
            padding-top: 0px;
        }

        form {
            width: 700px;
            padding: 40px;
            background: rgba(0, 0, 0, 0.3);
            box-sizing: border-box;
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.5);
            border-radius: 10px;
            text-align: center;
        }

        p {
            color: white;
            margin: 10px 0 20px;
            font-family: 'Poppins', sans-serif;
        }

        input[type="text"] {
            width: 50%;
            text-align: center;
            padding: 10px;
            margin-bottom: 40px;
            border: none;
            border-radius: 5px;
            box-shadow: 0 0 5px #289bb8, 0 0 25px #289bb8, 0 0 50px #289bb8;
        }

        input[type="submit"] {
            cursor: pointer;
            width: 50%;
            padding: 10px 50px;
            background: transparent;
            color: white;
            border: 1px solid white;
            border-radius: 15px;
            font-family: 'Poppins', sans-serif;
            box-shadow: 0 0 5px #289bb8, 0 0 25px #289bb8, 0 0 50px #289bb8, 0 0 100px #289bb8;
            transition: background-color 0.5s ease;
        }

        input[type="submit"]:hover {
            background: #289bb8;
            box-shadow: 0 0 5px #289bb8, 0 0 25px #289bb8, 0 0 50px #289bb8, 0 0 100px #289bb8;
        }
        
        #captcha {
            font-size: 30px;
            font-family: monospace;
            user-select: none;
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
        </style>
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
    <body style="display:flex; align-items:center; justify-content:center;">
        <jsp:include page="/WEB-INF/header.jsp"/> 
        <input type="hidden" id="refreshed" value="no">
        <script type="text/javascript">
               onload=function(){
                   var e=document.getElementById("refreshed");
                   if(e.value=="no")e.value="yes";
                   else{e.value="no";location.reload();}
               }
        </script>
        <h1>User Verification</h1>
        <form action="captchaVerif" method="POST">
            <p id="captcha" oncopy="preventCopy(event)">${sessionScope.captcha}</p>
            <p>Enter the CAPTCHA:</p>
            <input type="text" id="captchaVerif" name="captchaVerif" placeholder="Verify captcha..." autocomplete="off"/>
            <input type="submit" id="submit" value="Submit" class="btn"/>
        </form>
        <jsp:include page="/WEB-INF/footer.jsp"/> 
    </body>
</html>
