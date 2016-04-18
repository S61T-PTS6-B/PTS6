<%-- 
    Document   : NawList
    Created on : 15-mrt-2016, 15:15:29
    Author     : koenv
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.NAW"%>
<%@page import="dao.NawDAOImp"%>
<%@page import="java.util.List"%>
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
                <li><a class="active" href="NawList">Personen</a></li>
                <li><a href="CarTrackerList">Cartrackers</a></li>
                <li><a href="MileageList">Mileages</a></li>
            </ul>
        </div>
        <div id="wrapper">
            <div id="divborder">   
                <h1>Gebruikerlijst</h1>
                <ul>
                    <c:forEach var="naws" items="${naws}">
                        <form action="PersonalData" method="post">
                            <input type="hidden" name="bsn" value="${naws.bsn}">
                            <button class="myButton" type="submit"><c:out value="${naws.firstname}"/></c> <c:out value="${naws.lastname}"/></c></button>

                        </form>
                    </c:forEach>
                </ul>   
            </div>
        </div>
    </body>
</html>
