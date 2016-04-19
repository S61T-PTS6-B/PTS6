<%-- 
    Document   : changecartracker
    Created on : 21-mrt-2016, 10:15:53
    Author     : koenv
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.NAW"%>
<%@page import="model.CarTracker"%>
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
                <li><a href="NawList">Personen</a></li>
                <li><a href="CarTrackerList">Cartrackers</a></li>
                <li><a href="MileageList">Mileages</a></li>
                <li><a class="activeLegacy" href="#">Change</a></li>
            </ul>
        </div>
        <div id="wrapper">
            <div id="divborder">

                <div id="changeCar">
                    <center><h1>Verander cartracker</h1></center>

                    <form class= "pure-form" action="MileageChange" method="POST">
                        <p>Mileage Rate:  <input onClick="this.select();" type="text" name="mileagerate" placeholder="${mar.mileageRate}" /> <input type="hidden" name="id" value="${mar.id}"/><input type="submit"></p>
                    </form>
                    <form class= "pure-form" action="RegioChange" method="POST">
                        <p>Regio: <input onClick="this.select();" type="text" name="regio" placeholder="${mar.regio}"/><input type="hidden" name="id" value="${mar.id}"/><input type="submit"></p>
                    </form>
                    <form class= "pure-form" action="CategoryChange" method="POST">
                        <p>Price category: <input onClick="this.select();" type="text" name="pricecategory" placeholder="${mar.pricecategory}" /><input type="hidden" name="id" value="${mar.id}"/><input type="submit"></p>
                    </form>
                    <form class= "pure-form" action="IntervalChange" method="POST">
                        <p>Tijdsinterval: <input onClick="this.select();" type="text" name="interval" placeholder="${mar.tijdsinterval}"/><input type="hidden" name="id" value="${mar.id}"/><input type="submit"></p>
                    </form>
                </div>
            </div>
        </div>
        <br> 
</body>
</html>
