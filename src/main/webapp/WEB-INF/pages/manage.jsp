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
                /*
                 * 
                 * Known bugs: Wanneer een form gecleared word is de data nog niet weg, wanneer dus een leeg veld wordt gevult en op de wijzig knop gedrukt word zal deze wijziging bij de laatst aangeklikte persoon worden volbracht.
                 */

                var GlobalBSN = "";

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
                    if (isNaN(price.value)) {
                        alert("Prijs bestaat alleen uit cijfers");
                        valid = false;
                    }
                    if (!hasLetter(brand)) {
                        alert("Gelieve alleen letters in te voeren bij Merk auto")
                    }
                    if (valid) {
                        alert("Cartracker toegevoegd");
                    }

                    return valid;
                }

                function clearData() {
                    document.getElementById("persoonadd").reset();

                    document.getElementById("bsn").disabled = false;
                    document.getElementById("firstname").disabled = false;
                    document.getElementById("lastname").disabled = false;
                    document.getElementById("address").disabled = false;
                    document.getElementById("housenumber").disabled = false;
                    document.getElementById("zipcode").disabled = false;
                    document.getElementById("city").disabled = false;
                    document.getElementById("telephone").disabled = false;
                    document.getElementById("email").disabled = false;
                }

                function populateData() {
                    var e = document.getElementById("personList");
                    var strUser = e.options[e.selectedIndex].value;
                    if (strUser === null) {
                        console.log("BSN IS LEEG");
                    }
                    console.log(strUser);
                    $.ajax({
                        type: "post",
                        url: "Manage", //this is my servlet
                        data: {OptionBSN: strUser}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("firstname").value = msg.firstname;
                            document.getElementById("lastname").value = msg.lastname;
                            document.getElementById("address").value = msg.address;
                            document.getElementById("housenumber").value = msg.housenumber;
                            document.getElementById("zipcode").value = msg.zipcode;
                            document.getElementById("city").value = msg.city;
                            document.getElementById("telephone").value = msg.telephone;
                            document.getElementById("email").value = msg.email;

                            document.getElementById("bsn").disabled = true;
                            document.getElementById("firstname").disabled = true;
                            document.getElementById("lastname").disabled = true;
                            document.getElementById("address").disabled = true;
                            document.getElementById("housenumber").disabled = true;
                            document.getElementById("zipcode").disabled = true;
                            document.getElementById("city").disabled = true;
                            document.getElementById("telephone").disabled = true;
                            document.getElementById("email").disabled = true;

                            $.ajax({
                                type: "post",
                                url: "FillCT", //this is my servlet
                                data: {OptionBSN: strUser}, //Wat moet hier
                                success: function (ctevt) {

                                    console.log("Dit is de output:" + ctevt);
                                    msg = ctevt;



                                }
                            });

                        }

                    });


                }

                function PopulateDataCT() {
                    var e = document.getElementById("personList");
                    var strUser = e.options[e.selectedIndex].value;

                }

                function ChangeEnabler(fieldName) {
                    document.getElementById(fieldName).disabled = false;
                }

                function ChangeFirstname() {
                    var newname = document.getElementById("firstname").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeFirstname", //this is my servlet
                        data: {BSN: GlobalBSN, NewFirstname: newname}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("firstname").value = msg.firstname;

                            document.getElementById("firstname").disabled = true;


                        }
                    });
                }

                function ChangeLastname() {
                    var newname = document.getElementById("lastname").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeLastname", //this is my servlet
                        data: {BSN: GlobalBSN, NewLastname: newname}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("lastname").value = msg.lastname;

                            document.getElementById("lastname").disabled = true;


                        }
                    });
                }

                function ChangeAddress() {
                    var newstreet = document.getElementById("address").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeAddress", //this is my servlet
                        data: {BSN: GlobalBSN, NewAddress: newstreet}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("address").value = msg.address;

                            document.getElementById("address").disabled = true;


                        }
                    });
                }

                function ChangeNumber() {
                    var newnumber = document.getElementById("housenumber").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeNumber", //this is my servlet
                        data: {BSN: GlobalBSN, NewNumber: newnumber}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("housenumber").value = msg.housenumber;

                            document.getElementById("housenumber").disabled = true;


                        }
                    });
                }

                function getStreet() {
                    $.ajax({
                        type: "get",
                        url: "https://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&key=AIzaSyBjg-pNlIXixNkFDELNvG0QOe-qD21XU_k",
                        succes: function (response) {
                            console.log(response);
                        }
                    });
                }

                function ChangeZipcode() {
                    var newzipcode = document.getElementById("zipcode").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeZipcode", //this is my servlet
                        data: {BSN: GlobalBSN, NewZipcode: newzipcode}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("zipcode").value = msg.zipcode;

                            document.getElementById("zipcode").disabled = true;


                        }
                    });
                }

                function ChangeCity() {
                    var newcity = document.getElementById("city").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeCity", //this is my servlet
                        data: {BSN: GlobalBSN, NewCity: newcity}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("city").value = msg.city;

                            document.getElementById("city").disabled = true;


                        }
                    });
                }

                function ChangeTelephone() {
                    var newtelephone = document.getElementById("telephone").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeTelephone", //this is my servlet
                        data: {BSN: GlobalBSN, NewTelephone: newtelephone}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("telephone").value = msg.telephone;

                            document.getElementById("telephone").disabled = true;


                        }
                    });
                }

                function ChangeMail() {
                    var newmail = document.getElementById("email").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeMail", //this is my servlet
                        data: {BSN: GlobalBSN, NewMail: newmail}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsn").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("email").value = msg.mail;

                            document.getElementById("email").disabled = true;


                        }
                    });
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

	<button onclick="getStreet()" >Test street</button>
	<button onclick="clearData()" ></button>
        <div id="wrappercenter">
	    <p><h1>Personen:</h1></p>
	<select id= "personList" name="personList" size="${countnaws}" onchange="populateData()">

	    <c:forEach var="naws" items="${naws}">
		    <option id="OptionBSN" name="${naws.bsn}" value="${naws.bsn}"><c:out value="${naws.firstname}"/></c> <c:out value="${naws.lastname}"/></c></option> 
	    </c:forEach>
	</select>

	<div id="persoonWrapper">
	    <div id="divborder">
		<p><h1>Persoon toevoegen: </h1></p>
		<form id="persoonadd" class= "pure-form" action="AddPerson" method="POST" onsubmit="return nawvalidate();">
		    <p>Burger Service Nummer: <br /> <input id="bsn" type="text" name="bsn"></p>
		    <p>First name: <br /> <input id="firstname" type="text" name="firstname" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('firstname')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeFirstname()" /> </p> 
		    <p>Last name: <br /> <input id="lastname" type="text" name="lastname" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('lastname')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeLastname()" /></p>
		    <p class="street">Street:  Housenumber: <br /><input  id="address" type="text" name="address" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('address')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeAddress()" />
			<input  id="housenumber" type="text" name="number" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('housenumber')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeNumber()" /></p>
		    <p>Zipcode: <br /> <input  id="zipcode" type="text" name="zipcode" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('zipcode')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeZipcode()" /></p>
		    <p>City: <br /> <input  id="city" type="text" name="city" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('city')" /><img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeCity()" /></p>
		    <p>Telephone: <br /> <input  id="telephone" type="text" name="telephone" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('telephone')" /><img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeTelephone()" /></p>
		    <p>Email: <br /> <input id="email" type="text" name="email" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('email')" /><img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeMail()" /></p>
		    <input id="myBtn" onclick="showDiv()" type="submit" href="NawList">
		</form>
	    </div>
	</div>

	<div id="cartrackerWrapper">
	    <div id="divbordercar">
		<select id= "cartrackerList" name="cartrackerList" size="${countcartrackers}" onchange="populateDataCT()">
		    <c:forEach var="cartracker" items="${cartrackers}">
			    <option id="OptionBSN" name="${cartracker.id}" value="${cartracker.id}"><c:out  value="${cartracker.licenseplate}"/></c>, <c:out value="${cartracker.brandcar}"/></c>,  <c:out value="${cartracker.modelcar}"/></c></option> 
		    </c:forEach>
		</select>
	    </div>
	</div>
	<!--
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
	-->
    </div>
</body>
</html>
