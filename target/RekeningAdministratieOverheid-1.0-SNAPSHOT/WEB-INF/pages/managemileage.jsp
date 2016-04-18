<%-- 
    Document   : addMileage
    Created on : 18-apr-2016, 10:30:56
    Author     : koenv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <body>
        <div id="nav" class='balk'>
            <ul>
                <li><a class="active" href="Manage">Manage</a></li>
                <li><a href="NawList">Personen</a></li>
                <li><a href="CarTrackerList">Cartrackers</a></li>
            </ul>
        </div>
        <div id="wrappercenter">
            <div id="persoonWrapper">
                <div id="divborder">
                    <p><h1>Kilometertarief toevoegen: </h1></p>
                    <form id="persoonadd" class= "pure-form" action="AddMileage" method="POST">
                        <p>Mileage Rate: <input type="text" name="mar" /></p>
                        <p>Regio: <input type="text" name="regio" /></p>
                        <p>Price category: <input type="text" name="pricecategory" /></p>
                        <p>Time interval: <input type="text" name="interval" /></p>
                        <input id="myBtn" onclick="showDiv()" type="submit" href="MileageList">
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
