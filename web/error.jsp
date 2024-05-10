<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Error" />
        <title>Error</title>
        <style><%@include file="/WEB-INF/css/error.css"%></style>
    </head>

    <body>
        <div class="wrapper">
            <div id="content">
                <h1>OOPS!</h1>
                <p><%= request.getSession().getAttribute("error_message") %></p>
                <div class="forms">
                    <form class="form">
                        <button type="button" class="btn" onclick="history.back()">Return</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>