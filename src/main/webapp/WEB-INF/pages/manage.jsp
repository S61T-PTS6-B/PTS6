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
            function hasLetter(str) {
                // check for characters between a and z
                // i flag makes it case insensitive
                return /[a-z]/i.test(str);
            }

            function hasSpecialLetter(str) {
                // check for characters between a and z
                // i flag makes it case insensitive
                return /[a-z']/i.test(str);
            }

            function validateEmail(str) {
                var re = ^(([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+)?$;
                return re.test(str);
            }

            function nawvalidate()
            {
                var bsn = document.getElementById("bsn");
                var firstname = document.getElementById("firstname");
                var lastname = document.getElementById("lastname");
                var address = document.getElementById("address");
                var city = document.getElementById("city");
                var email = document.getElementById("email");
                var telephone = document.getElementById("telephone");
                var bsnValue = document.getElementById("bsn").value;
                var valid = true;
                if (bsn.value.length <= 0 || firstname.value.length <= 0)
                {
                    alert("Don't leave the field empty!");
                    valid = false;
                }
                if (bsnValue.length != 9)
                {
                    alert("Een bsn bestaat uit 9 cijfers");
                    valid = false;
                } else {
                    if (isNaN(bsnValue)) {
                        alert("Enter a number");
                        valid = false;
                    }
                }
                if (!hasLetter(firstname))
                {
                    alert("Een voornaam mag alleen uit letters bestaan");
                    valid = false;
                }
                if (!hasLetter(lastname)) {
                    alert("Een achternaam mag alleen uit letters bestaan");
                    valid = false;
                }
                if (!hasSpecialLetter(address)) {
                    alert("Een adres mag alleen uit letters bestaan");
                    valid = false;
                }
                if (!hasSpecialLetter(city)) {
                    alert("Een stad mag alleen uit letters bestaan");
                    valid = false;
                }
                if (!validateEmail(email)) {
                    alert("Voer een juist e-mail adres in");
                    valid = false;
                }
                if (isNaN(telephone)) {
                    alert("Enter a number");
                    valid = false;
                }


                return valid;
            }
            ;

        </script>
        <style>
            #progress { 
                display: none;
                color: green; 
            }
        </style> 
    </head>

    <body>
        <div id="nav" class='balk'>
            <ul>
                <li><a class="active" href="Manage">Manage</a></li>
                <li><a href="ManageMileage">Manage mileage</a></li>
                <li><a href="NawList">Personen</a></li>
                <li><a href="CarTrackerList">Cartrackers</a></li>
                <li><a href="MileageList">Mileages</a></li>

            </ul>
        </div>
        <div id="wrappercenter">
            <div id="persoonWrapper">
                <div id="divborder">
                    <p><h1>Persoon toevoegen: </h1></p>
                    <form id="persoonadd" class= "pure-form" action="AddPerson" method="POST" onsubmit="return nawvalidate();">
                        <p>Burger Service Nummer: <input id="bsn" type="text" name="bsn" /></p>
                        <p>First name: <input id="firstname" type="text" name="firstname" /></p>
                        <p>Last name: <input id="lastname" type="text" name="lastname" /></p>
                        <p>Address: <input  id="address" type="text" name="address" /></p>
                        <p>Number: <input  id="housenumber" type="text" name="number" /></p>
                        <p>Zipcode: <input  id="zipcode" type="text" name="zipcode" /></p>
                        <p>City: <input  id="city" type="text" name="city" /></p>
                        <p>Telephone: <input  id="telephone" type="text" name="telephone" /></p>
                        <p>Email: <input id="email" type="text" name="email" /></p>
                        <input id="myBtn" onclick="showDiv()" type="submit" href="NawList">
                    </form>
                </div>
            </div>

            <div id="cartrackerWrapper">
                <div id="divbordercar">
                    <p><h1>CarTracker toevoegen: </h1></p>
                    <form class="pure-form" action="AddCarTracker" method="POST" onsubmit="return ctvalidate();">
                        Personenlijst:
                        <select name="bsn">
                            <c:forEach var="naws" items="${naws}">
                                <option name="${naws.bsn}" value="${naws.bsn}"><c:out value="${naws.firstname}"/></c> <c:out value="${naws.lastname}"/></c></option>
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
