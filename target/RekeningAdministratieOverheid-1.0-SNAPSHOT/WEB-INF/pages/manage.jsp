<%-- 
    Document   : manage
    Created on : 28-mrt-2016, 15:14:25
    Author     : koenv
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" />
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            $(document).ready(function () {

                $('#myTooltip').tooltip({
                    open: function (e) {
                        setTimeout(function () {
                            $(e.target).tooltip('close');
                        }, 1000);
                    }
                });

                $('#myBtn').click(function () {
                    $('#myTooltip').tooltip('open');
                });
            });
        </script>
    </head>

    <body>
        <a id="myTooltip" title="Persoon succesvol toegevoegd!"></a>
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
                    <p><h1>Persoon toevoegen: </h1></p>
                    <form class= "pure-form" action="AddPerson" method="POST">
                        <p>First name: <input type="text" name="firstname" /></p>
                        <p>Last name: <input type="text" name="lastname" /></p>
                        <p>Address: <input type="text" name="address" /></p>
                        <p>Number: <input type="text" name="number" /></p>
                        <p>Zipcode: <input type="text" name="zipcode" /></p>
                        <p>City: <input type="text" name="city" /></p>
                        <p>Telephone: <input type="text" name="telephone" /></p>
                        <p>Email: <input type="text" name="email" /></p>
                        <input id="myBtn" type="submit" href="NawList">
                    </form>
                </div>
            </div>

            <div id="cartrackerWrapper">
                <div id="divbordercar">
                    <p><h1>CarTracker toevoegen: </h1></p>
                    <form class="pure-form" action="AddCarTracker" method="POST">
                        Personenlijst:
                        <select name="id">
                            <c:forEach var="naws" items="${naws}">
                                <option name="${naws.id}" value="${naws.id}"><c:out value="${naws.firstname}"/></c> <c:out value="${naws.lastname}"/></c></option>
                            </c:forEach>
                        </select>
                        <p>Prize Category: <input type="text" name="category" /></p>
                        <p>License plate: <input type="text" name="license" /></p>
                        <p>Car model: <input type="text" name="carmodel" /></p>
                        <p>Car brand: <input type="text" name="carbrand" /></p>
                        <input id="button" type="submit" href="CarTrackerList">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
