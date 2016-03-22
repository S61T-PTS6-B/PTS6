<%-- 
    Document   : index
    Created on : 15-mrt-2016, 14:10:53
    Author     : koenv
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>    
        <div id="wrapper">
        <p><h1>Persoon toevoegen: </h1></p><p> <a class="linkMember" href="NawList"> Memberlist </a> </p>
        <form class= "pure-form" action="CarTrackerAdm" method="POST">
            <p>First name: <input type="text" name="firstname" /></p>
            <p>Last name: <input type="text" name="lastname" /></p>
            <p>Address: <input type="text" name="address" /></p>
            <p>Number: <input type="text" name="number" /></p>
            <p>Zipcode: <input type="text" name="zipcode" /></p>
            <p>City: <input type="text" name="city" /></p>
            <p>Telephone: <input type="text" name="telephone" /></p>
            <p>Prize Category: <input type="text" name="category" /></p>
            <p>License plate: <input type="text" name="license" /></p>
            <p>Car model: <input type="text" name="carmodel" /></p>
            <p>Car brand: <input type="text" name="carbrand" /></p>
            <input type="submit">
        </form>
        </div>
        

    </body>
</html>
