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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
                
        <p> <a href="CarTrackerAdm"> KLIK HIER MAX JA HAHAHAHHA </a> </p>
        
        <select>
         <c:forEach var="cartrackers" items="${cartrackers}">
                 <option name="${cartrackers.getId()}" class="commentOnPost"><c:out value="${cartrackers.getNaw().getFirstname()}"/></c></option>
         </c:forEach>
        </select>
        
        <p><h1>Cartracker toevoegen: </h1></p>
        <form action="CarTrackerAdm" method="POST">
            <p>First name: <input type="text" name="firstname" /></p>
            <p>Last name: <input type="text" name="lastname" /></p>
            <p>Address: <input type="text" name="address" /></p>
            <p>Number: <input type="text" name="number" /></p>
            <p>Zipcode: <input type="text" name="zipcode" /></p>
            <p>City: <input type="text" name="city" /></p>
            <p>Telephone: <input type="text" name="telephone" /></p>
            <input type="submit">
        </form>
    

    </body>
</html>
