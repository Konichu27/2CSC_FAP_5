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
        <jsp:include page="/WEB-INF/header.jsp"/>
        <div class="wrapper">
            <div id="content">
                <h1>OOPS!</h1>
                <p>You must be logged in to access this page.</p>
                <div class="forms">
                    <form class="form">
                        <a href="index.jsp">
                            <button type="button" class="btn">Return</button>
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>