<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
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

        .robot {
            position: fixed;
            top: 0;
            right: 0;
            z-index: -1;
            width: 700px;
            height: auto;
            opacity: 0.5;
        }

        .container {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 8px;
        }

        .form {
            width: 700px;
            padding: 40px;
            background: rgba(0, 0, 0, 0.3);
            box-sizing: border-box;
            box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
            border-radius: 10px;
            text-align: center;

        }

        .ERROR p {
            color: white;
            text-align: center;
            padding: 1px;
            margin-top: 80px;
            margin-bottom: 5px;
            font-size: 80px;
            font-weight: 800;
            z-index: 999;
            font-family: Poppins;
            line-height: 1em
        }

        h2 {
            margin-top: 0;

            margin-bottom: 50px;
            color: white;
            text-align: center;
            font-family: Poppins;
            line-height: 1em
        }


        .btn {
            width: 50%;
            margin-top: 30px;
            margin-bottom: 10px;
            background: transparent;
            color: #fff;
            border-radius: 15px;
            box-shadow: 0 0 5px #289bb8, 0 0 25px #289bb8, 0 0 50px #289bb8, 0 0 100px #289bb8;
            position: relative;
            display: inline-block;
            padding: 10px 50px;
            font-size: 14px;
            text-decoration: none;
            overflow: hidden;
            transition: .5s;
            letter-spacing: 2px;
            border: 1px solid white;
            border-radius: 15px;
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }

        .btn:hover {
            background: #289bb8;
            color: #fff;
            border-radius: 15px;
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

        .header {
            color: white;
            text-align: center;
            padding: 40px;
            font-size: 15px;
            z-index: 999;
            font-family: Poppins;
            line-height: 1em
        }

        .header p {
            margin-bottom: 10spx;
        }

        .footer {
            color: white;
            text-align: center;
            padding: 50px;
            font-size: 13px;
            font-family: Poppins;
            line-height: 0.5em;


        }

        .footer p {
            margin-bottom: 0;
        }
    </style>

    <title>Login Failure</title>
</head>

<body style="display:flex; align-items:center; justify-content:center;">
    <jsp:include page="/WEB-INF/header.jsp"/> 
    <!--<img src="ERRobot.png" alt="Sad Robot 3d" class="robot">-->
    <div class="container">
        <div class="form">
            <div class="ERROR">
                <p>Login Failure</p>
                <h2>Username is correct, but password is blank.</h2>
            </div>
            <a href="index.jsp">
                <input class="btn" type="submit" id="submit" value="Return">
            </a>
        </div>
    </div>
    <br>
    <jsp:include page="/WEB-INF/footer.jsp"/> 
</body>

</html>