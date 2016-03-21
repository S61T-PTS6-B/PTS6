<%-- 
    Document   : cartrackerlist
    Created on : 15-mrt-2016, 15:09:43
    Author     : koenv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
                
        <select>
         <c:forEach var="cartrackers" items="${cartrackers}">
                 <option name="${cartrackers.getId()}"><c:out value="${cartrackers.getNaw().getFirstname()}"/></c> <c:out value="${cartrackers.getNaw().getLastname()}"/></c></option>
         </c:forEach>
        </select>
         
    </body>
</html>
