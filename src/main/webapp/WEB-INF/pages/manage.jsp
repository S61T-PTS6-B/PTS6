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
                $('form').submit(function (/*DOMEvent*/ e) {
                    e.preventDefault();

                    var url = $(this).attr('action'),
                            data = $(this).serialize();
                    $.post(url, data, function (response) {
                        alert("Test!");
                    });
                });
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
//                    var re = ^(([a - zA - Z0 - 9_. - ]) + @([a - zA - Z0 - 9_. - ]) + \.([a - zA - Z]) + ([a - zA - Z]) + )?$;
//                            return re.test(str);
                    return true;
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
                        alert("Graag alle velden invullen!");
                        valid = false;
                    }
                    if (bsnValue.length != 9)
                    {
                        alert("Een bsn bestaat uit 9 cijfers");
                        valid = false;
                    } else {
                        if (isNaN(bsnValue)) {
                            alert("Voer een correct burgerservicenummer in");
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
                    if (isNaN(telephone.value)) {
                        alert("Voer een telefoonnummer in");
                        valid = false;
                    }
                    if (valid) {
                        alert("Persoon toegevoegd!");
                    }


                    return valid;
                }
                

                function ctvalidate() 
		{
                    var price = document.getElementById("priceCategory");
                    var license = document.getElementById("licensePlate");
                    var model = document.getElementById("model");
                    var brand = document.getElementById("brand");
		    var valid = true;
		    
		    if (price.value.length <= 0 || license.value.length <= 0 || model.value.length <= 0 || brand.value.length <= 0)
                    {
                        alert("Graag alle velden invullen!");
                        valid = false;
                    }
		    if(isNaN(price.value)) {
			    alert("Prijs bestaat alleen uit cijfers");
			    valid = false;
		    }
		    if(!hasLetter(brand)) {
			    alert("Gelieve alleen letters in te voeren bij Merk auto")
		    }
		    if(valid) {
			    alert("Cartracker toegevoegd");
		    }
		    
		    return valid;
                }

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
                        <p>Street: <input  id="address" type="text" name="address" /></p>
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
                        <p>Prize Category: <input id="priceCategory" type="text" name="category" /></p>
                        <p>License plate: <input id="licensePlate" type="text" name="license" /></p>
                        <p>Car model: <input id="model" type="text" name="carmodel" /></p>
                        <p>Car brand: <input id="brand" type="text" name="carbrand" /></p>
                        <input id="button" type="submit" href="CarTrackerList">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
