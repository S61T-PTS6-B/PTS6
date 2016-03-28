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
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <div id="nawlist">  
            <h1>Gebruikerlijst</h1>
            <ul>
                <c:forEach var="naws" items="${naws}">
                    <form action="PersonalData" method="post">
                        <button class="myButton" type="submit"><c:out value="${naws.firstname}"/></c> <c:out value="${naws.lastname}"/></c></button>
                        <input type="hidden" name="id" value="${naws.id}">
                    </form>
                </c:forEach>
            </ul>   
        </div>
        <p>
            <a href="Manage"> Persoon toevoegen </a>
        </p>
    </body>
</html>
