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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <ul>
            <c:out value="${theUser.naw.getFirstname()}"/>
            <c:out value="${theUser.naw.getLastname()}"/>
            <c:out value="${theUser.naw.getTelephone()}"/>
            <c:out value="${theUser.naw.getCity()}"/>
            <c:out value="${theUser.getKenteken()}"/>
            <c:out value="${theUser.getModelAuto()}"/>
            <c:out value="${theUser.getMerkAuto()}"/>
            <c:out value="${theUser.getTariefCategorie()}"/>
            
        </ul>
    </body>
</html>
