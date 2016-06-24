<%-- 
    Document   : login
    Created on : 24-jun-2016, 14:38:23
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
	<div id="login">
            <div id="loginheader">
                <h2 id="headertext">De Rekeningrijder</h2>
		<form action="Manage" method="POST">
		    <input type="text" placeholder="Username" id="username" />
		    <input type="password" placeholder="Password" id="password" />
		    <input type="submit" value="Log in"/>

		</form>
            </div>
    </body>
</html>


