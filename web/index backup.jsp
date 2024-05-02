<%-- 
    Document   : index.jsp
    Created on : 02 25, 24, 5:20:40 PM
    Author     : Dayao, Leonne Matthew H. // UST - 1CSC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"  type="text/css" href="styles.css">
        <style>
           @import url(https://fonts.googleapis.com/css?family=Poppins:300);

          body {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background: linear-gradient(#30142b, #2772a1);
            display: flex;
            flex-direction: column;
            min-height: 100dvh;
          }

          .login-page {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom:10px;
          }

          .form {
            width: 400px;
            padding: 40px;
            background: rgba(0, 0, 0, 0.5);
            box-sizing: border-box;
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
            border-radius: 10px;
          }

          .form input[type="text"],
          .form input[type="password"]{
            width: 100%;
            padding: 10px 0;
            font-size: 13px;
            color: #fff;
            margin-bottom: 30px;
            border: none;
            border-bottom: 1px solid #fff;
            outline: none;
            background: transparent;
          }

          h2, h3 {
            color: white; 
            text-align: center;
          }
        

          .form .message {
            margin: 15px 0 0;
            color: #b3b3b3;
            font-size: 12px;
          }

          .form .message a {
            color: #289bb8;
            text-decoration: none;
          }

          .form .register-form {
            display: none;
          }

          .btn {
            width: 100%;
            margin-bottom: 30px;
            background: transparent;
            position: relative;
            display: inline-block;
            padding: 10px 50px;
            color: whitesmoke;
            font-size: 14px;
            text-decoration: none;
            overflow: hidden;
            transition: .5s;
            margin-top: 15px;
            letter-spacing: 2px;
            border: 1px solid white;
            border-radius: 5px;
          }

          .btn:hover {
            background: #289bb8;
            color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 5px #289bb8, 0 0 25px #289bb8, 0 0 50px #289bb8, 0 0 100px #289bb8;
          }

          .btn span {
            position: absolute;
            display: block;
          }

          .btn span:nth-child(1) {
            top: 0;
            left: -100%;
            width: 100%;
            height: 2px;
            background: linear-gradient(90deg, transparent, #289bb8);
            animation: btn-anim1 1s linear infinite;
          }
          
          input {
              padding:2px;
          }

          @keyframes btn-anim1 {
            0% {
              left: -100%;
            }
            50%, 100% {
              left: 100%;
            }
          }

          .btn span:nth-child(2) {
            top: -100%;
            right: 0;
            width: 2px;
            height: 100%;
            background: linear-gradient(180deg, transparent, #289bb8);
            animation: btn-anim2 1s linear infinite;
            animation-delay: .25s;
          }

          @keyframes btn-anim2 {
            0% {
              top: -100%;
            }
            50%, 100% {
              top: 100%;
            }
          }
          
           .header {
            color: white;
            text-align: center;
            padding: 40px;
            font-size: 15px;
            z-index: 999;
            font-family: Poppins;
            line-height:1em
          }

          .header p {
            margin: 5px;
          }

          .footer {
            color: white;
            text-align: center;
            padding: 80px;
            font-size: 13px;
            font-family: Poppins;
            line-height:0.5em;


          }
          
          .footer p {
              margin-bottom: 3px;
          }
        </style>
          
        <title>Login Page</title>
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
    <body style="display:flex; align-items:center; justify-content:center;">
        <jsp:include page="/WEB-INF/header.jsp"/> 
        <div class="login-page">
            <div class="form">
            <form action="login" method="POST">
            <h2>Login Page</h2>
                <input id="uname" name="uname" type="text" placeholder="Username" autocomplete="off">
                <input id="pword" name="pword" type="password" placeholder="Password" autocomplete="off">
                
                <input class="btn" type="submit" id="submit" value="Submit">
            </form>
          </div>
        </div>
        <br>
        <jsp:include page="/WEB-INF/footer.jsp"/> 
    </body>
</html>