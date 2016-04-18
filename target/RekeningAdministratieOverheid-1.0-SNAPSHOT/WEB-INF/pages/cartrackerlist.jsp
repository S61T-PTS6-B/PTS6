<%-- 
    Document   : cartrackerlist
    Created on : 15-mrt-2016, 15:09:43
    Author     : koenv
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <div id="nav" class='balk'>
            <ul>
                <li><a href="Manage">Manage</a></li>
                <li><a href="ManageMileage">Manage mileage</a></li>
                <li><a href="NawList">Personen</a></li>
                <li><a class="active" href="CarTrackerList">Cartrackers</a></li>
                <li><a href="MileageList">Mileages</a></li>
            </ul>
        </div>
        <div id="wrapper">
            <div id="divborder">  
                <h1>CarTrackerlijst</h1>
                <ul>
                    <c:forEach var="CTs" items="${CTs}">
                        <form action="ChangeCT" method="post">
                            <button class="myButton" type="submit"><c:out value="${CTs.getId()}"/></c> <c:out value="${CTs.getPrizeCategory()}"/></c><c:out value="${CTs.getLicensePlate()}"/></c></button>
                            <input type="hidden" name="id" value="${CTs.id}">
                        </form>
                    </c:forEach>
                </ul>   
            </div>
        </div>
    </body>
</html>
