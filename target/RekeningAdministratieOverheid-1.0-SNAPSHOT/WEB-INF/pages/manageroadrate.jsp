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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/elements.css" />
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

                window.onload = function () {
                    fixSize();
                    document.getElementById("roadnameshow").disabled = true;
                    document.getElementById("dateinshow").disabled = true;
                    document.getElementById("dateoutshow").disabled = true;
                    document.getElementById("starttimeshow").disabled = true;
                    document.getElementById("endtimeshow").disabled = true;

                    document.getElementById("rateshow").disabled = true;
                };
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

                function PopulateData() {
                    var e = document.getElementById("roadList");
                    var r = document.getElementById("roadrateList");

                    var strRoadRate = r.options[r.selectedIndex].value;
                    var strRoad = e.options[e.selectedIndex].value;

                    if (strRoad === null) {
                        console.log("BSN IS LEEG");
                    }
                    console.log(strRoad);
                    $.ajax({
                        type: "post",
                        url: "ManageRoad", //this is my servlet
                        data: {OptionRoad: strRoad}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            GlobalRoad = msg.road.id;
                            document.getElementById("roadnameshow").value = msg.road.id;
                            document.getElementById("dateinshow").value = msg.timestamp_in;
                            document.getElementById("dateoutshow").value = msg.timestamp_out;
                            document.getElementById("starttimeshow").value = msg.time_start;
                            document.getElementById("endtimeshow").value = msg.time_end;
                            document.getElementById("rateshow").value = msg.rate;

                            document.getElementById("bsnshow").disabled = true;
                            document.getElementById("firstnameshow").disabled = true;
                            document.getElementById("lastnameshow").disabled = true;
                            document.getElementById("addressshow").disabled = true;
                            document.getElementById("housenumbershow").disabled = true;
                            e.size += +1;
                        }

                    });


                }

                $(function () {
                    $("#dateoutshow").datepicker();
                });

                function PopulateDataRR() {
                    var e = document.getElementById("roadList");
                    var roadname = e.options[e.selectedIndex].value;
                    $.ajax({
                        type: "post",
                        url: "FillFieldsRR", //this is my servlet
                        data: {OptionRR: roadname}, //Wat moet hier
                        success: function (ctevt) {

                            msg = JSON.parse(ctevt);
                            var select = document.getElementById("roadrateList");
                            jQuery.each(msg, function () {


                                var myNode = document.getElementById("roadrateList");
                                myNode.innerHTML = '';
                                myNode.value = "";
                                var lengte = msg.roadrates.length;
                                for (var i = 0; i < lengte; i++) {
                                    var opt = document.createElement('option');
                                    opt.value = msg.roadrates[i].datein;
                                    opt.innerHTML = msg.roadrates[i].timestart + ", " + msg.roadrates[i].timeend;
                                    select.appendChild(opt);
                                }



                            })
                        }

                    });
                }

                function ChangeEnabler(fieldName) {
                    document.getElementById(fieldName).disabled = false;
                    document.getElementById(fieldName).focus();
                    PopulateDataCT();
                }
                function reload(fieldname) {
                    var container = document.getElementById(fieldname);
                    var content = container.innerHTML;
                    container.innerHTML = content;
                }

                function ChangeDateOut() {
                    var newdate = document.getElementById("dateoutshow").value;
                    var olddate = document.getElementById("dateinshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeDateout",
                        data: {Road: GlobalRoad, NewDate: newdate, OldDate: olddate},
                        success: function (evt) {
                            console.log(evt);
                            msg = evt;
                            document.getElementById("dateoutshow").value = msg.dateout;
                            document.getElementById("dateoutshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function fixSize() {
                    $('#roadList').attr('size', $('select option').length);
                    console.log("Fixed size!");
                }

                //Function To Display Popup
                function div_show() {
                    document.getElementById('abc').style.display = "block";
                }
                function div_showct() {
                    document.getElementById('abct').style.display = "block";
                }
//Function to Hide Popup
                function div_hide() {
                    document.getElementById('abc').style.display = "none";
                    document.getElementById('abct').style.display = "none";
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
                <li><a href="Manage">Beheer</a></li>
		<li><a href="ManageCartracker">Beheer Cartrackers</a></li>
		<li><a class="active" href="ManageRoadRate">Beheer wegen</a></li>
		<!--                <li><a href="ManageMileage">Beheer kilometertarieven</a></li>
				<li><a href="MileageList">Beheer kilometertarieven</a></li>-->

            </ul>
        </div>

	<div id="abc">
	    <!-- Popup Div Starts Here -->
	    <div id="popupContact">
		<!-- Add road Form -->
		<form action="AddRoad" id="popform" method="post" name="popform" class="popform">
		    <img id="close" src="${pageContext.request.contextPath}/icons/close.png" onclick ="div_hide()">
		    <h2 class="poph2">Weg toevoegen</h2>
		    <hr class="pophr">
		    <p>Naam: <br /> <input class="popf" id="roadname" type="text" name="roadname"></p>
		    <input class="popbutton" type="submit" onclick="fixSize();" href="#"/>
		</form>
	    </div>
	    <!-- Popup Div Ends Here -->
	</div>

	<div id="abct">
	    <!-- Popup Div Starts Here -->
	    <div id="popupContact">
		<!-- Add roadrate Form -->
		<form action="AddRoadRate" id="popform" method="post" name="popform" class="popform">
		    <img id="close" src="${pageContext.request.contextPath}/icons/close.png" onclick ="div_hide()">
		    <h2 class="poph2">Wegtarief toevoegen</h2>
		    <hr class="pophr">
		    <input id="roadname" type="text" value="replace" name="roadname"/>
		    <p>Datum invoer: <br/><input id="datein" type="text" name="datein" /></p>
		    <p>Datum einde: <br/><input id="dateend" type="text" name="dateend" /></p>
		    <p>Start tijd: <br/><input id="starttime" type="text" name="starttime" /> </p>
		    <p>Eind tijd: <br/><input id="endtime" type="text" name="endtime" /> </p>
		    <p>Tarief: <br/><input id="rate" type="text" name="rate" /></p>
		    <input class="popbutton" type="submit" onclick="fixSize();" href="#"/>
		</form>
	    </div>
	    <!-- Popup Div Ends Here -->
	</div>

	<!--<button onclick="getStreet()" >Test street</button>
	<button onclick="clearData()" ></button> -->
        <div id="wrappercenter">

	    <select id= "roadList" name="roadList" size="${countroads}" onchange="PopulateDataRR();
                        fixSize();">

		<c:forEach var="roads" items="${roads}">
			<option id="OptionBSN" name="${roads.id}" value="${roads.id}"><c:out value="${roads.id}"/></c> </option> 
		</c:forEach>

	    </select>
	    <div id="roadrates">
		<img id="addico" onclick="div_show()" src="${pageContext.request.contextPath}/icons/road-add.png" href="#" width="150%" />
	    </div>
	    <div id="persoonWrapper">

		<div id="divborder">
		    <select id= "roadrateList" name="roadrateList" size="5" onchange="PopulateData()">

		    </select>
		    <p><h1>Weg gegevens </h1></p>
		    <img id="addico" onclick="div_showct()" src="${pageContext.request.contextPath}/icons/time-add.png" href="#" width="5%" />
		    <form id="persoonadd" class= "pure-form" action="AddPerson" method="POST" onsubmit="return nawvalidate();">
			<p>Naam: <br /> <input id="roadnameshow" type="text" name="roadname"></p>
			<p>Datum invoer: <br /> <input id="dateinshow" type="text" name="firstname" /></p> 
			<p>Datum uitvoer: <br /> <input id="dateoutshow" type="datetime" name="lastname" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('dateoutshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeDateOut()" /></p>
			<p>Start tijd:<br /><input  id="starttimeshow" type="text" name="address" /> </p>
			<p>Eind tijd: <br /> <input  id="endtimeshow" type="text" name="zipcode" /></p>
			<p>Tarief: <br /> <input id="rateshow" type="text" name="rate" /> </p>
		    </form>
		</div>

	    </div>
	</div>
    </body>
</html>
