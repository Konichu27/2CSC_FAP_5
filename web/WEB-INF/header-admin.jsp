<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

        #logo {
            display: inline-block;
            padding-top: 0.3125rem;
            padding-bottom: 0.3125rem;
            margin-top: 1rem;
            margin-left: 5rem;
            margin-right: 1rem;
            margin-bottom: 1rem;
            line-height: inherit;
            white-space: nowrap;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
            position: relative;
        }

        /* Hide the links inside the navigation menu (except for logo/home) */
        .topnav #myLinks {
            display: none;
        }

        /* Style navigation menu links */
        .topnav a {
            color: white;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 1.2rem;
            display: block;
            
        }

        
        .topnav a.icon {
            display: block;
            position: absolute;
            right: 0;
            top: 0;
            align-self: center;
            margin-top:2.5%;
            margin-right:2%;
            font-size: 1.2rem;
        }

        /* Add a grey background color on mouse-over */
        .topnav a.icon:hover {
            
            border-radius: 4px;
            background-color: orange;
        }

        /* Style the active link (or home/logo) */
        .active {
            background-color: orange;
            color: white;
        }

        .myLinks{
            font-family: 'Poppins', sans-serif;
            text-align: center;
        }

        .other:hover{
            background-color: white;
            color: #333;
            transition: background-color 0.3s;

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
            font-weight: 600;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #fff;
            color: orange;
            border-radius: 4px;
            cursor: pointer;
            font-family: Poppins;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }
        

    </style>
</head>

<body>
    <div class="topnav" id="myTopnav">
        <img id="logo" src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png"> </a>

        <div id="myLinks" class="myLinks">
            <a href="applicants" class="other">Applicants</a>
            <a href="accounts" class="other">Accounts</a>
            <a href="/2CSC_FAP_5/logout" class="btn">Sign Out</a>
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