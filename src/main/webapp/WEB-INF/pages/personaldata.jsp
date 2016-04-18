<%-- 
    Document   : personaldata
    Created on : 17-mrt-2016, 11:15:23
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
                <li><a class="activeLegacy" href="#">Enkel persoon</a></li>
            </ul>
        </div>
        <div id="wrapper">
            <div id="divborder">
                <h1>Personal Data</h1>           
                <table>
                    <thead>
                        <tr>
                            <th> 
                                BSN
                            </th>
                            <th>
                                First name
                            </th>
                            <th >
                                Last name
                            </th>
                            <th>
                                Telephone
                            </th>
                            <th>
                                City
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 
                                <c:out value="${theUser.bsn}"/>
                            </td>
                            <td >
                                <c:out value="${theUser.firstname}"/>
                            </td>
                            <td>
                                <c:out value="${theUser.lastname}"/>
                            </td>
                            <td>
                                <c:out value="${theUser.getTelephone()}"/>
                            </td>
                            <td>
                                <c:out value="${theUser.getCity()}"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table>
                    <thead>
                        <tr>
                            <th>
                                License plate
                            </th>
                            <th >
                                Brand
                            </th>
                            <th>
                                Model
                            </th>
                            <th>
                                Prize category
                            </th>
                            <th>
                                Verander
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="theCTs" items="${theCTs}">
                            <tr>
                                <td >
                                    <c:out value="${theCTs.getLicensePlate()}"/>
                                </td>
                                <td>
                                    <c:out value="${theCTs.getBrandCar()}"/>
                                </td>
                                <td>
                                    <c:out value="${theCTs.getModelCar()}"/>
                                </td>
                                <td>
                                    <c:out value="${theCTs.getPrizeCategory()}"/>
                                </td>
                                <td>
                                    <form action="ChangeCT" method="POST">
                                        <button class="myButton" type="submit"> Verander</button>
                                        <input type="hidden" name="id" value="${theCTs.getId()}">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
