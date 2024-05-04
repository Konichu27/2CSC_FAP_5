<%-- Document : successtest Created on : 02 23, 24, 2:16:05 PM Author : Dayao, Leonne Matthew H. // UST - 1CSC --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/WEB-INF/styles.css">
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


        .container {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 5px;
        }

        .form {
            width: 700px;
            padding: 30px;
            background: rgba(0, 0, 0, 0.3);
            box-sizing: border-box;
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
            border-radius: 10px;
            text-align: center;

        }

        .CONTENT p {
            color: white;
            text-align: center;
            padding: 1px;
            margin-top: 10px;
            margin-bottom: 5px;
            font-size: 20px;
            font-weight: 800;
            z-index: 999;
            font-family: Poppins;
            line-height: 1em
        }

        .title p {
            color: white;
            text-align: center;
            padding: 1px;
            margin-top: 80px;
            margin-bottom: 5px;
            font-size: 60px;
            font-weight: 800;
            z-index: 999;
            font-family: Poppins;
            line-height: 1em
        }

        h1, h3{
            margin-top: 0;
            margin-bottom: 10px;
            color: white;
            text-align: center;
            font-family: Poppins;
            line-height: 1em
        }


        .btn {
            width: 50%;
            margin-top: 30px;
            margin-bottom: 0px;
            background: transparent;
            color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 5px #289bb8, 0 0 25px #289bb8, 0 0 50px #289bb8, 0 0 100px #289bb8;
            position: relative;
            display: inline-block;
            padding: 10px 10px;
            font-size: 14px;
            text-decoration: none;
            overflow: hidden;
            transition: .5s;
            letter-spacing: 2px;
            border: 1px solid white;
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }

        .btn:hover {
            background: #289bb8;
            color: #fff;
            border-radius: 10px;
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
            padding: 2px;
        }

        @keyframes btn-anim1 {
            0% {
                left: -100%;
            }

            50%,
            100% {
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

            50%,
            100% {
                top: 100%;
            }
        }
    </style>
        </head>
        <%  // Removes cache from success.jsp to prevent form resubmission
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache"); 
        response.setHeader("Expires","0");
        response.setDateHeader("Expires",-1);
        %>
        <body style="display:flex; align-items:center; justify-content:center;">
            <div class="container">
                <div class="form">
                    <div class="CONTENT">
                        <div class="title">
                    <p><strong>LOGIN SUCCESS!</strong></p></div>
                    <br>
                    <h3>You are currently logged in as:</h3>
                    <p><u>${sessionScope.uname}</u></p>
                    <br>
                    <h3>Account Type:</h3>
                    <p><u>${sessionScope.urole}</u></p>
                    </div>
                    <br>
                    <br>
                    <form action="downloadReport" method="POST">
                        <input class="btn" type="submit" id="submit" value="Download PDF Report">
                    </form>
                    <form action="logout" method="POST">
                        <input class="btn" type="submit" id="submit" value="Logout">
                    </form>
                </div>
            </div>
        </body>

        <br>
        </html>