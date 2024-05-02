<!DOCTYPE html>
<html>
    <head>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Poppins:300);

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
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <p><% out.print(getServletContext().getInitParameter("Subject")); %></p>    
            <p><% out.print(getServletContext().getInitParameter("Section")); %></p>   
            <p><% out.print(getServletContext().getInitParameter("Name"));%></p>
        </header> 
    </body>
</html>
