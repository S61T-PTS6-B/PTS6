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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Personal Data</h1>           
                <table>
                    <thead>
                    <tr>
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
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td >
                            <c:out value="${theCT.getKenteken()}"/>
                        </td>
                        <td>
                            <c:out value="${theCT.getMerkAuto()}"/>
                        </td>
                        <td>
                            <c:out value="${theCT.getModelAuto()}"/>
                        </td>
                        <td>
                            <c:out value="${theCT.getTariefCategorie()}"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
                        <a href="ChangeCT">Change</a><br />
                        <a href="NawList">Terug naar personenlijst</a>
    </body>
</html>
