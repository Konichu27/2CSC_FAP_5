<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Sign Up" />
    <title>Sign Up Page</title>
    <style><%@include file="/WEB-INF/css/signup.css"%></style>
    <link rel="shortcut icon"
        href="https://activelearning.ph/wp-content/uploads/2021/03/cropped-favicon-1-192x192.png" />

</head>

<body>
    <jsp:include page="/WEB-INF/header.jsp"/>

    <div class="wrapper">
        <div id="content">
            <div class="SignIn">
                <h1 class="entry-title">Sign Up</h1> 
                <p>Begin your journey with us</p>
            </div>

            <div id="login-form-container">
                <div class="forms">
                    <form class="form">
                        <div class="username">
                            <input id="username-input" name="username" type="text" placeholder="Username*" required
                                autocomplete="off">
                        </div>
                        <div class="password">
                            <input id="password-input" name="password" type="password" placeholder="Password*" required
                                autocomplete="off">
                        </div>
                        <div class="confirm-password">
                            <input id="password-input" name="password" type="password" placeholder="Confirm Password*"
                                required autocomplete="off">
                        </div>
                        <div class="button">
                            <button type="submit" class="btn">Sign Up</button>
                        </div>
                        <div class="login">
                            <a title="LogIn" href="index.jsp" class="login"><u><p>Already have an account? Log In</u></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>