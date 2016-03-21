<%-- 
    Document   : changecartracker
    Created on : 21-mrt-2016, 10:15:53
    Author     : koenv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <form class= "pure-form" action="PrizeCategoryChange" method="POST">
            <p>Prize Category: <input type="text" name="category" /><input type="submit"></p>
        </form>
        <form class= "pure-form" action="LicenseChange" method="POST">
            <p>License plate: <input type="text" name="license" /><input type="submit"></p>
        </form>
        <form class= "pure-form" action="CarModelChange" method="POST">
            <p>Car model: <input type="text" name="carmodel" /><input type="submit"></p>
        </form>
        <form class= "pure-form" action="CarBrandChange" method="POST">
            <p>Car brand: <input type="text" name="carbrand" /><input type="submit"></p>
        </form>
            
        
    </body>
</html>
