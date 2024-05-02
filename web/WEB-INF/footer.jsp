<!DOCTYPE html>
<html>
    <head>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Poppins:300);

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
    </head>
    <body>
        <footer class="footer">
            <p>Date: <%= getServletContext().getAttribute("currentDate")%></p>
            <p>Time: <%= getServletContext().getAttribute("currentTime")%></p>
            <p>Machine Problem IV</p>
        </footer>
    </body>
</html>
