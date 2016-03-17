<%-- 
    Document   : users
    Created on : 15-mrt-2016, 17:59:37
    Author     : koenv
--%>
<%@page import="model.KwetterUser"%>
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
                <a href="users.jsp">Hee</a>
        <p name="change" value="change">Verander dit!</p>
        <h1><c:out value="${theUser.getUsername()}"/></c></h1>
    </body>
</html>
