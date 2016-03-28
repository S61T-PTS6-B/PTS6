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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="changeCar">
            <center><h1>Verander cartracker</h1></center>

        <form class= "pure-form" action="PrizeCategoryChange" method="POST">
            <p>Prize Category:  <input onClick="this.select();" type="text" name="category" placeholder="${ct.getPrizeCategory()}" /><input type="submit"></p>
        </form>
        <form class= "pure-form" action="LicenseChange" method="POST">
            <p>License plate: <input onClick="this.select();" type="text" name="license" placeholder="${ct.getLicensePlate()}"/><input type="submit"></p>
        </form>
        <form class= "pure-form" action="CarModelChange" method="POST">
            <p>Car model: <input onClick="this.select();" type="text" name="carmodel" placeholder="${ct.getModelCar()}" /><input type="submit"></p>
        </form>
        <form class= "pure-form" action="CarBrandChange" method="POST">
            <p>Car brand: <input onClick="this.select();" type="text" name="carbrand" placeholder="${ct.getBrandCar()}"/><input type="submit"></p>
        </form>
        </div>
        
    </body>
</html>
