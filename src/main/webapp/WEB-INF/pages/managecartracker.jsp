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
                    document.getElementById("cartrackerList").innerHTML = "Cartrackers zijn leeg";
                    document.getElementById("bsnshow").disabled = true;
                    document.getElementById("firstnameshow").disabled = true;
                    document.getElementById("lastnameshow").disabled = true;
                    document.getElementById("addressshow").disabled = true;
                    document.getElementById("housenumbershow").disabled = true;
                    document.getElementById("zipcodeshow").disabled = true;
                    document.getElementById("cityshow").disabled = true;
                    document.getElementById("telephoneshow").disabled = true;
                    document.getElementById("emailshow").disabled = true;


                    document.getElementById("priceCategoryshow").disabled = true;
                    document.getElementById("licensePlateshow").disabled = true;
                    document.getElementById("brandshow").disabled = true;
                    document.getElementById("modelshow").disabled = true;
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

                function populateData() {
                    var e = document.getElementById("carList");

                    var strUser = e.options[e.selectedIndex].value;
                    if (strUser === null) {
                        console.log("BSN IS LEEG");
                    }
                    console.log(strUser);
                    $.ajax({
                        type: "post",
                        url: "ManageNAW", //this is my servlet
                        data: {OptionKenteken: strUser}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("firstnameshow").value = msg.firstname;
                            document.getElementById("lastnameshow").value = msg.lastname;
                            document.getElementById("addressshow").value = msg.address;
                            document.getElementById("housenumbershow").value = msg.housenumber;
                            document.getElementById("zipcodeshow").value = msg.zipcode;
                            document.getElementById("cityshow").value = msg.city;
                            document.getElementById("telephoneshow").value = msg.telephone;
                            document.getElementById("emailshow").value = msg.email;
                            document.getElementById("bsnhide").value = msg.bsn;
                            document.getElementById("priceCategoryshow").value = " ";
                            document.getElementById("licensePlateshow").value = " ";
                            document.getElementById("brandshow").value = " ";
                            document.getElementById("modelshow").value = " ";

                            document.getElementById("bsnshow").disabled = true;
                            document.getElementById("firstnameshow").disabled = true;
                            document.getElementById("lastnameshow").disabled = true;
                            document.getElementById("addressshow").disabled = true;
                            document.getElementById("housenumbershow").disabled = true;
                            document.getElementById("zipcodeshow").disabled = true;
                            document.getElementById("cityshow").disabled = true;
                            document.getElementById("telephoneshow").disabled = true;
                            document.getElementById("emailshow").disabled = true;

                            $.ajax({
                                type: "post",
                                url: "FillFieldsCT", //this is my servlet
                                data: {OptionBSN: GlobalBSN}, //Wat moet hier
                                success: function (ctevt) {
                                    document.getElementById("priceCategoryshow").value = ctevt.pricecategory;
                                    document.getElementById("licensePlateshow").value = ctevt.licenseplate;
                                    document.getElementById("brandshow").value = ctevt.brandcar;
                                    document.getElementById("modelshow").value = ctevt.modelcar;

                                    document.getElementById("priceCategoryshow").disabled = true;
                                    document.getElementById("licensePlateshow").disabled = true;
                                    document.getElementById("brandshow").disabled = true;
                                    document.getElementById("modelshow").disabled = true;


                                }
                            });

                            $.ajax({
                                type: "post",
                                url: "FillCT", //this is my servlet
                                data: {OptionBSN: GlobalBSN}, //Wat moet hier
                                success: function (ctevt) {
                                    msg = JSON.parse(ctevt);
                                    var select = document.getElementById("cartrackerList");
                                    jQuery.each(msg, function () {


                                        var myNode = document.getElementById("cartrackerList");
                                        myNode.innerHTML = '';
                                        myNode.value = "";
                                        var lengte = msg.cartrackers.length;
                                        for (var i = 0; i < lengte; i++) {
                                            var opt = document.createElement('option');
                                            opt.value = msg.cartrackers[i].licensePlate;
                                            opt.innerHTML = msg.cartrackers[i].licensePlate + ", " + msg.cartrackers[i].brandCar + ", " + msg.cartrackers[i].modelCar;
                                            select.appendChild(opt);
                                        }

                                    });
                                },
                                error: function (xhr, status, error) {
                                    var err = eval("(" + xhr.responseText + ")");
                                    alert(err.Message);
                                }
                            });

                        }

                    });


                }

                function PopulateDataCT() {
                    var e = document.getElementById("cartrackerList");
                    var strUser = e.options[e.selectedIndex].value;
                    $.ajax({
                        type: "post",
                        url: "FillFieldsCT", //this is my servlet
                        data: {OptionBSN: strUser}, //Wat moet hier
                        success: function (ctevt) {
                            document.getElementById("priceCategoryshow").value = ctevt.pricecategory;
                            document.getElementById("licensePlateshow").value = ctevt.licenseplate;
                            document.getElementById("brandshow").value = ctevt.brandcar;
                            document.getElementById("modelshow").value = ctevt.modelcar;

                            document.getElementById("priceCategoryshow").disabled = true;
                            document.getElementById("licensePlateshow").disabled = true;
                            document.getElementById("brandshow").disabled = true;
                            document.getElementById("modelshow").disabled = true;


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

                function ChangeFirstname() {
                    var newname = document.getElementById("firstnameshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeFirstname", //this is my servlet
                        data: {BSN: GlobalBSN, NewFirstname: newname}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("firstnameshow").value = msg.firstname;
                            document.getElementById("firstnameshow").disabled = true;

                            location.reload();
                        }
                    });

                }

                function ChangeLastname() {
                    var newname = document.getElementById("lastnameshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeLastname", //this is my servlet
                        data: {BSN: GlobalBSN, NewLastname: newname}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("lastnameshow").value = msg.lastname;

                            document.getElementById("lastnameshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function ChangeAddress() {
                    var newstreet = document.getElementById("addressshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeAddress", //this is my servlet
                        data: {BSN: GlobalBSN, NewAddress: newstreet}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("addressshow").value = msg.address;

                            document.getElementById("addressshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function ChangeNumber() {
                    var newnumber = document.getElementById("housenumbershow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeNumber", //this is my servlet
                        data: {BSN: GlobalBSN, NewNumber: newnumber}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("housenumbershow").value = msg.housenumber;

                            document.getElementById("housenumbershow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function fixSize() {
                    $('#carList').attr('size', $('select option').length);
                    console.log("Fixed size!");
                    console.log(document.getElementById("bsnhide").value);
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
                    var newzipcode = document.getElementById("zipcodeshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeZipcode", //this is my servlet
                        data: {BSN: GlobalBSN, NewZipcode: newzipcode}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("zipcodeshow").value = msg.zipcode;

                            document.getElementById("zipcodeshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function ChangeCity() {
                    var newcity = document.getElementById("cityshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeCity", //this is my servlet
                        data: {BSN: GlobalBSN, NewCity: newcity}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("cityshow").value = msg.city;

                            document.getElementById("cityshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function ChangeTelephone() {
                    var newtelephone = document.getElementById("telephoneshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeTelephone", //this is my servlet
                        data: {BSN: GlobalBSN, NewTelephone: newtelephone}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("telephoneshow").value = msg.telephone;

                            document.getElementById("telephoneshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function ChangeMail() {
                    var newmail = document.getElementById("emailshow").value;
                    $.ajax({
                        type: "post",
                        url: "ChangeMail", //this is my servlet
                        data: {BSN: GlobalBSN, NewMail: newmail}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalBSN = msg.bsn;
                            document.getElementById("emailshow").value = msg.mail;

                            document.getElementById("emailshow").disabled = true;

                            location.reload();
                        }
                    });
                }

                function ChangePriceCategory() {
                    var newpricecategory = document.getElementById("pricecategory").value;
                    $.ajax({
                        type: "post",
                        url: "ChangePriceCategory", //this is my servlet
                        data: {LicensePlate: GlobalLP, NewPriceCategory: newpricecategory}, //Wat moet hier
                        success: function (evt) {

                            console.log(evt);
                            msg = evt;
                            document.getElementById("bsnshow").value = msg.bsn;
                            GlobalLP = msg.licensePlate;
                            document.getElementById("priceCategoryshow").value = msg.mail;

                            document.getElementById("priceCategoryshow").disabled = true;

                            location.reload();
                        }
                    });
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

                function SearchNAW() {
                    var s = document.getElementById('searchNAW').value;
                    $.ajax({
                        type: "post",
                        url: "SearchNAW", //this is my servlet
                        data: {NAW: s}, //Wat moet hier
                        success: function (evt) {
                            if (evt === "")
                            {
                                alert("Persoon niet gevonden.");
                            } else
                            {
                                msg = evt;
                                document.getElementById("bsnshow").value = msg.bsn;
                                GlobalBSN = msg.bsn;
                                document.getElementById("firstnameshow").value = msg.firstname;
                                document.getElementById("lastnameshow").value = msg.lastname;
                                document.getElementById("addressshow").value = msg.address;
                                document.getElementById("housenumbershow").value = msg.housenumber;
                                document.getElementById("zipcodeshow").value = msg.zipcode;
                                document.getElementById("cityshow").value = msg.city;
                                document.getElementById("telephoneshow").value = msg.telephone;
                                document.getElementById("emailshow").value = msg.email;
                                document.getElementById("bsnhide").value = msg.bsn;
                                document.getElementById("priceCategoryshow").value = " ";
                                document.getElementById("licensePlateshow").value = " ";
                                document.getElementById("brandshow").value = " ";
                                document.getElementById("modelshow").value = " ";


                                document.getElementById("bsnshow").disabled = true;
                                document.getElementById("firstnameshow").disabled = true;
                                document.getElementById("lastnameshow").disabled = true;
                                document.getElementById("addressshow").disabled = true;
                                document.getElementById("housenumbershow").disabled = true;
                                document.getElementById("zipcodeshow").disabled = true;
                                document.getElementById("cityshow").disabled = true;
                                document.getElementById("telephoneshow").disabled = true;
                                document.getElementById("emailshow").disabled = true;

                                getCT(s);
                            }

                        }
                    });
                }

                function SearchCT() {
                    var s = document.getElementById('searchCT').value;
                    $.ajax({
                        type: "post",
                        url: "SearchCT", //this is my servlet
                        data: {CT: s}, //Wat moet hier
                        success: function (evt) {
                            if (evt === "")
                            {
                                alert("Cartracker niet gevonden.");
                            } else
                            {
                                ctevt = evt;
                                document.getElementById("priceCategoryshow").value = ctevt.pricecategory;
                                document.getElementById("licensePlateshow").value = ctevt.licenseplate;
                                document.getElementById("brandshow").value = ctevt.brandcar;
                                document.getElementById("modelshow").value = ctevt.modelcar;


                                document.getElementById("bsnshow").disabled = true;
                                document.getElementById("firstnameshow").disabled = true;
                                document.getElementById("lastnameshow").disabled = true;
                                document.getElementById("addressshow").disabled = true;
                                document.getElementById("housenumbershow").disabled = true;
                                document.getElementById("zipcodeshow").disabled = true;
                                document.getElementById("cityshow").disabled = true;
                                document.getElementById("telephoneshow").disabled = true;
                                document.getElementById("emailshow").disabled = true;
                            }

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
                <li><a href="Manage">Beheer</a></li>
		<li><a class="active" href="ManageCartracker">Beheer Cartrackers</a></li>
		<li><a href="ManageRoadRate">Beheer wegen</a></li>
		<!--                <li><a href="ManageMileage">Beheer kilometertarieven</a></li>
				<li><a href="MileageList">Beheer kilometertarieven</a></li>-->

            </ul>
        </div>

	<div id="abc">
	    <!-- Popup Div Starts Here -->
	    <div id="popupContact">
		<!-- Contact Us Form -->
		<form action="AddPerson" id="popform" method="post" name="popform" class="popform">
		    <img id="close" src="${pageContext.request.contextPath}/icons/close.png" onclick ="div_hide()">
		    <h2 class="poph2">Persoon toevoegen</h2>
		    <hr class="pophr">
		    <p>Burger Service Nummer: <br /> <input class="popf" id="bsn" type="text" name="bsn"></p>
		    <p>Voornaam: <br /> <input class="popf"  id="firstname" type="text" name="firstname" /> </p> 
		    <p>Achternaam: <br /> <input class="popf" id="lastname" type="text" name="lastname" /></p>
		    <p >Straat: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nr: <br /><input  class="popf" id="address" type="text" name="address" />
			<input class="popf"  id="housenumber" type="text" name="number" /></p>
		    <p>Postcode: <br /> <input class="popf"  id="zipcode" type="text" name="zipcode" /></p>
		    <p>Stad: <br /> <input class="popf"  id="city" type="text" name="city" /></p>
		    <p>Telefoonnummer: <br /> <input class="popf"  id="telephone" type="text" name="telephone" /></p>
		    <p>Email: <br /> <input class="popf"  id="email" type="text" name="email" /></p>
		    <input class="popbutton" type="submit" onclick="fixSize();" href="#"/>
		</form>
	    </div>
	    <!-- Popup Div Ends Here -->
	</div>
	<input type="text" id="searchCT"/><button onclick="SearchCT()">Zoeken</button>
	<div id="abct">
	    <!-- Popup Div Starts Here -->
	    <div id="popupContact">
		<!-- Contact Us Form -->
		<form action="AddCarTracker" id="popform" method="post" name="popform" class="popform">
		    <img id="close" src="${pageContext.request.contextPath}/icons/close.png" onclick ="div_hide()">
		    <h2 class="poph2">CarTracker toevoegen</h2>
		    <hr class="pophr">
		    <input id="bsnhide" type="hidden" value="replace" name="bsnhide"/>
		    <p>Tariefcategorie: <br/><input id="priceCategory" type="text" name="category" /></p>
		    <p>Kenteken: <br/><input id="licensePlate" type="text" name="license" /></p>
		    <p>Automerk: <br/><input id="brand" type="text" name="carbrand" /> </p>		    
		    <p>Automodel: <br/><input id="model" type="text" name="carmodel" /> </p>
		    <input class="popbutton" type="submit" onclick="fixSize();" href="#"/>
		</form>
	    </div>
	    <!-- Popup Div Ends Here -->
	</div>

	<!--<button onclick="getStreet()" >Test street</button>
	<button onclick="clearData()" ></button> -->
        <div id="wrappercenter">



	    <select id= "carList" name="personList" size="${countcartrackers}" onchange="populateData();
                        fixSize();">

		<c:forEach var="cartrackers" items="${cartrackers}">
			<option id="OptionBSN" name="${cartrackers.licensePlate}" value="${cartrackers.licensePlate}"><c:out value="${cartrackers.licensePlate}"/></c> (<c:out value="${cartrackers.brandCar}"/></c> <c:out value="${cartrackers.modelCar}"/></c>)</option> 
		</c:forEach>
	    </select>
	    <div id="persoonWrapper">
		<div id="divborder">
		    <p><h1>NAW gegevens </h1></p>
		    <img id="addico" onclick="div_show()" src="${pageContext.request.contextPath}/icons/add.png" href="#" width="5%" />
		    <form id="persoonadd" class= "pure-form" action="AddPerson" method="POST" onsubmit="return nawvalidate();">
			<p>Burger Service Nummer: <br /> <input id="bsnshow" type="text" name="bsn"></p>
			<p>Voornaam: <br /> <input id="firstnameshow" type="text" name="firstname" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('firstnameshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeFirstname()" /> </p> 
			<p>Achternaam: <br /> <input id="lastnameshow" type="text" name="lastname" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('lastnameshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeLastname()" /></p>
			<p class="street">Straat:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nr: <br /><input  id="addressshow" type="text" name="address" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('addressshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeAddress()" />
			    <input  id="housenumbershow" type="text" name="number" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('housenumbershow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeNumber()" /></p>
			<p>Postcode: <br /> <input  id="zipcodeshow" type="text" name="zipcode" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('zipcodeshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeZipcode()" /></p>
			<p>Stad: <br /> <input  id="cityshow" type="text" name="city" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('cityshow')" /><img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeCity()" /></p>
			<p>Telefoonnummer: <br /> <input  id="telephoneshow" type="text" name="telephone" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('telephoneshow')" /><img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeTelephone()" /></p>
			<p>Email: <br /> <input id="emailshow" type="text" name="email" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('emailshow')" /><img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeMail()" /></p>
			<!--<input id="myBtn" onclick="showDiv()" type="submit" href="NawList"> -->
		    </form>
		</div>
	    </div>

	    <div id="cartrackerWrapper">
		<div id="divbordercar">
		    <select id= "cartrackerList" name="cartrackerList" size="5" onchange="PopulateDataCT()">

		    </select>
		</div>
	    </div>
	    <div id="cartrackerWrapper2">
		<div id="divbordercar2">
		    <p><h1>Cartracker gegevens</h1></p>
		    <img id="addico" onclick="div_showct()" src="${pageContext.request.contextPath}/icons/car_add.png" href="#" width="5%" />
		    <form class="pure-form">
			<p>Tariefcategorie: <br/><input id="priceCategoryshow" type="text" name="category" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('priceCategoryshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangePriceCategory()" /> </p> </p>
			<p>Kenteken: <br/><input id="licensePlateshow" type="text" name="license" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('licensePlateshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeLicensePlate()" /> </p> </p>
			<p>Automerk: <br/><input id="brandshow" type="text" name="carbrand" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('brandshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeCarBrand()" /> </p> </p>		    
			<p>Automodel: <br/><input id="modelshow" type="text" name="carmodel" /><img href="#" src="${pageContext.request.contextPath}/icons/change.png" width="3%" onclick ="ChangeEnabler('modelshow')" /> <img href="#" src="${pageContext.request.contextPath}/icons/check.png" width="3%" onclick ="ChangeCarModel()" /> </p> </p>

		    </form>
		</div>
	    </div>

	</div>
    </body>
</html>
