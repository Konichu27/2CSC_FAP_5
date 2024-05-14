<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Error" />
        <title>Error</title>
        <style><%@include file="/css/error.css"%></style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/template/header.html"/>
        <div class="wrapper">
            <div id="content">
                <h1>OOPS!</h1>
                <p>404: Page not found.</p>
                <div class="forms">
                    <form class="form">
                        <button type="button" class="btn" onclick="history.back()">Return</button>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/template/footer.html"/>
    </body>
</html>