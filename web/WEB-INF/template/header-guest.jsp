<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
            @import url(https://fonts.googleapis.com/css?family=Poppins:400);

            #logo {
                display: inline-block;
                padding-top: 0.3125rem;
                padding-bottom: 0.3125rem;
                margin-top: 1rem;
                margin-left: 5rem;
                margin-right: 1rem;
                line-height: inherit;
                white-space: nowrap;
            }

            @media screen and (max-width: 600px) {
                .topnav a:not(:first-child) {
                    display: none;
                }

                .topnav a.icon {
                    float: right;
                    display: block;
                }
            }

            @media screen and (max-width: 600px) {
                .topnav.responsive {
                    position: relative;
                }

                .topnav.responsive a.icon {
                    position: absolute;
                    right: 0;
                    top: 0;
                }

                .topnav.responsive a {
                    float: none;
                    display: block;
                    text-align: left;
                }
            }

            nav {
                flex-grow: 1;
                font-family: 'Poppins', sans-serif;

            }

            nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: flex-end;
            }

            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
            }

            nav ul li {
                margin: 0 1vw;
            }

            .topnav {
                background-color: #252323 !important;
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                align-items: center;
                position: absolute;
                padding: 1rem 1rem;
                position: sticky;
            }

            a{
                color: white;
                text-decoration:none;
                font-size: 1.2rem;
            }



            .topnav #hover:hover {
                background-color: #1F9BDE;
                color: white;
                transition: background-color 0.3s;

            }

            .topnav a.active {
                color: orange;
            }

            nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: flex-end;
            }

            .page{

                padding: 0.2rem 1rem;
                margin: 1% 1%;
                border: none;

                border-radius: 4px;
                cursor: pointer;
                font-family: Poppins;

                font-weight: 400;
                transition: background-color 0.3s ease;

            }

            .btn {
                padding: 0.2rem 1rem;
                margin: 1% 1%;
                border: none;
                background-color: orange;
                color: #fff;
                border-radius: 4px;
                cursor: pointer;
                font-family: Poppins;

                font-weight: 400;
                transition: background-color 0.3s ease;
            }
    </style>
</head>

<body>
    <div class="topnav" id="myTopnav">
        <img id="logo" src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png"> </a>

        <div id="myLinks" class="myLinks">
            <a href="#" class="other">Applicants</a>
            <a href="#" class="other">Accounts</a>
            <a href="#"class="other">Archives</a>
            <a href="#" class="btn">Sign Out</a>
        </div>
        <!-- "Hamburger menu" / "Bar icon" to toggle the navigation links -->
        <a href="javascript:void(0);" class="icon" onclick="myFunction()">
            <i class="fa fa-bars"></i>
        </a>
    </div>

    <script>
        function myFunction() {
            var x = document.getElementById("myLinks");
            if (x.style.display === "block") {
                x.style.display = "none";
            } else {
                x.style.display = "block";
            }
        }
    </script>

</body>

</html>