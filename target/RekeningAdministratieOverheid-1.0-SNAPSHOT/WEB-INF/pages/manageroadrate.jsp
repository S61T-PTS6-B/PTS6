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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/elements.css" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.timepicker.css" />


        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.timepicker.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
                /*
                 *
                 * Known bugs: Wanneer een form gecleared word is de data nog niet weg, wanneer dus een leeg veld wordt gevult en op de wijzig knop gedrukt word zal deze wijziging bij de laatst aangeklikte persoon worden volbracht.
                 */

                var GlobalBSN = "";
                var GlobalRoad = "";
                var dateinuse = "";

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
                    var r = document.getElementById("activeroadrateList");

                    var strRoadRate = r.options[r.selectedIndex].value;
                    var strRoad = e.options[e.selectedIndex].value;

                    if (strRoad === null) {
                        console.log("BSN IS LEEG");
                    }
                    console.log(strRoad);
                    $.ajax({
                        type: "post",
                        url: "ManageRoad", //this is my servlet
                        data: {OptionRoad: strRoad, OptionRR: strRoadRate}, //Wat moet hier
                        success: function (evt) {
                            msg = evt;
                            var correctstart = msg.timestart.substring(11, 16);
                            var correctend = msg.timeend.substring(11, 16);
                            dateinuse = msg.date_in;

                            var correctdatein = msg.date_in.substring(4, 10);
                            var correctyearin = msg.date_in.substring(25, 29);
                            var correctdateout = msg.dateout.substring(4, 10);
                            var correctyearout = msg.dateout.substring(25, 29);
                            var correctin = correctdatein + ", " + correctyearin;
                            var correctout = correctdateout + ", " + correctyearout;
                            console.log(correctin);
                            console.log(correctout);
                            console.log(correctstart);

                            document.getElementById("roadnameshow").value = msg.naam;
                            document.getElementById("dateinshow").value = correctin;
                            console.log("Goede outdate: " + correctout);
                            document.getElementById("dateoutshow").value = correctout;
                            document.getElementById("starttimeshow").value = correctstart;
                            document.getElementById("endtimeshow").value = correctend;
                            document.getElementById("rateshow").value = msg.rate;
                            e.size += +1;
                        }

                    });


                }

                $(function () {
                    $(function () {
                        $('#starttime').timepicker('show');
                        $('#endtime').timepicker('show');
                        $("#dateoutshow").datepicker({
                            dateFormat: "yy-mm-dd 00:00:00"
                        });
                        $("#datein").datepicker({
                            dateFormat: "yy-mm-dd 00:00:00",
                            defaultDate: "+1w",
                            changeMonth: true,
                            numberOfMonths: 3,
                            onClose: function (selectedDate) {
                                $("#dateout").datepicker("option", "minDate", selectedDate);
                            }
                        });
                        $("#dateend").datepicker({
                            dateFormat: "yy-mm-dd 00:00:00",
                            defaultDate: "+1w",
                            changeMonth: true,
                            numberOfMonths: 3,
                            onClose: function (selectedDate) {
                                $("#datein").datepicker("option", "maxDate", selectedDate);
                            }
                        });
                        $("#dateend").datepicker({
                            dateFormat: "yy-mm-dd 00:00:00"
                        });

                        $("#dateoutshow").datepicker({
                            dateFormat: "yy-mm-dd 00:00:00"
                        });
                        $("#dateoutshow").datepicker({
                            dateFormat: "yy-mm-dd 00:00:00"
                        });
                    });
                });

                function PopulateDataActive() {
                    var e = document.getElementById("roadList");
                    var roadname = e.options[e.selectedIndex].value;
                    $.ajax({
                        type: "post",
                        url: "FillFieldsActiveRR", //this is my servlet
                        data: {OptionRR: roadname}, //Wat moet hier
                        success: function (ctevt) {
                            GlobalRoad = roadname;
                            msg = JSON.parse(ctevt);
                            var select = document.getElementById("activeroadrateList");
                            jQuery.each(msg, function () {


                                var myNode = document.getElementById("activeroadrateList");
                                myNode.innerHTML = '';
                                myNode.value = "";
                                var lengte = msg.roadrates.length;
                                for (var i = 0; i < lengte; i++) {
                                    var opt = document.createElement('option');
                                    opt.value = msg.roadrates[i].id;
                                    opt.innerHTML = "Begindatum: " + msg.roadrates[i].datein.substring(4, 10) + ", Einddatum: " + msg.roadrates[i].dateout.substring(4, 10) + " van " + msg.roadrates[i].timestart.substring(11, 16) + " tot " + msg.roadrates[i].timeend.substring(11, 16) ;
                                    select.appendChild(opt);
                                }



                            })
                        }

                    });

                }

                function PopulateDataRR() {
                    var e = document.getElementById("roadList");
                    var roadname = e.options[e.selectedIndex].value;
                    $.ajax({
                        type: "post",
                        url: "FillFieldsRR", //this is my servlet
                        data: {OptionRR: roadname}, //Wat moet hier
                        success: function (ctevt) {
                            GlobalRoad = roadname;
                            msg = JSON.parse(ctevt);
                            var select = document.getElementById("roadrateList");
                            jQuery.each(msg, function () {


                                var myNode = document.getElementById("roadrateList");
                                myNode.innerHTML = '';
                                myNode.value = "";
                                var lengte = msg.roadrates.length;
                                for (var i = 0; i < lengte; i++) {
                                    var opt = document.createElement('option');
                                    opt.value = msg.roadrates[i].id;
                                    opt.innerHTML = "Begindatum: " + msg.roadrates[i].datein.substring(4, 10) + ", Einddatum: " + msg.roadrates[i].dateout.substring(4, 10) + " van " + msg.roadrates[i].timestart.substring(11, 16) + " tot " + msg.roadrates[i].timeend.substring(11, 16) ;
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
                    var from = newdate.split("/");
                    var f = new Date(from[2], from[0] - 1, from[1]);
                    console.log(newdate + dateinuse + GlobalRoad);
                    $.ajax({
                        type: "post",
                        url: "ChangeDateout",
                        data: {Road: GlobalRoad, OutDate: newdate, InDate: dateinuse},
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
                    fillRoad();
                    fillAddRR();
                }


                function fillRoad() {
                    var roadElement = document.getElementById('roadname').value;
                    roadElement = GlobalRoad;
                    document.getElementById('roadname').innerHTML = GlobalRoad;
                    console.log("Dit is de vervanging:" + roadElement);
                }
//Function to Hide Popup
                function div_hide() {
                    document.getElementById('abc').style.display = "none";
                    document.getElementById('abct').style.display = "none";
                }
                function fillAddRR() {
                    var roadelement = document.getElementById('roadname');
                    roadelement.value = GlobalRoad;
                    console.log("Dit is de weg:" + roadelement.value);
                    roadelement.placeholder = GlobalRoad;
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
		    <p>Wegnaam: <input id="roadname" type="text" name="roadname" /></p>
		    <p>Datum invoer: <br/><input id="datein" type="text" name="datein" /></p>
		    <p>Datum einde: <br/><input id="dateend" type="text" name="dateend" /></p>
		    <p>Start tijd: <br/><input id="starttime" type="text" name="starttime" /> </p>
		    <p>Eind tijd: <br/><input id="endtime" type="text" name="endtime" /> </p>
		    <p>Tarief: <br/><input id="rate" type="text" name="rate" /></p>
		    <input class="popbutton" type="submit" onclick="fixSize(); PopulateDataRR();" href="#"/>
		</form>
	    </div>
	    <!-- Popup Div Ends Here -->
	</div>

	<!--<button onclick="getStreet()" >Test street</button>
	<button onclick="clearData()" ></button> -->
        <div id="wrappercenter">

	    <select id= "roadList" name="roadList" size="${countroads}" onchange="PopulateDataRR(); PopulateDataActive();
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
		    <h2>Actieve kilometertarieven</h2>
		    <select id= "activeroadrateList" name="roadrateList" size="5" onchange="PopulateData()">

		</select>
		    
		    <p><h2>Weg gegevens </h1></p>
		    <img id="addico" onclick="div_showct();
                                fillAddRR()" src="${pageContext.request.contextPath}/icons/time-add.png" href="#" width="5%" />
		    <form id="persoonadd" class= "pure-form" action="AddPerson" method="POST" onsubmit="return nawvalidate();">
			<p>Naam: <br /> <input id="roadnameshow" type="text" name="roadname"></p>
			<p>Datum invoer: <br /> <input id="dateinshow" type="text" name="firstname" /></p>
			<p>Verloopdatum: <br /> <input id="dateoutshow" type="text" name="lastname" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('dateoutshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeDateOut()" /></p>
			<p>Start tijd:<br /><input  id="starttimeshow" type="text" name="address" /> </p>
			<p>Eind tijd: <br /> <input  id="endtimeshow" type="text" name="zipcode" /></p>
			<p>Tarief: <br /> <input id="rateshow" type="text" name="rate" /> </p>
		    </form>
		</div>

	    </div>
	    <div id="roadratesInactive">
		<h2>Inactieve kilometertarieven</h2>
		<select id= "roadrateList" name="roadrateList" size="37" onchange="PopulateData()">

		</select>
	    </div>
	</div>
    </body>
</html>
