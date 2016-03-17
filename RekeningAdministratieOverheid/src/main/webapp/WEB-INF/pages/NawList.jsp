<%-- 
    Document   : NawList
    Created on : 15-mrt-2016, 15:15:29
    Author     : koenv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
                        
        <select>
         <c:forEach var="naw" items="${naws}">
                 <option name="${naws.getId()}"><c:out value="${naws.getFirstname()}"/></c></option>
         </c:forEach>
        </select>
    </body>
</html>
