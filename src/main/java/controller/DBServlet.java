/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import model.CarOwner;
import model.CarTracker;
import model.Invoice;
import model.NAW;
import model.Road;
import model.RoadRate;
import org.json.JSONException;
import service.ICarOwnerService;
import service.ICarTrackerService;
import service.INAWService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.IInvoiceService;
import service.IRoadRateService;
import service.IRoadService;

/**
 *
 * @author koenv
 */
@WebServlet(name = "CarTrackerAdm", urlPatterns = {"/CarTrackerAdm",
	"/ManageCartracker",
	"/Error",
	"/Manage",
	"/ManageRoadRate",
	"/FillCT",
	"/FillFieldsCT",
	"/FillFieldsRR",
	"/FillFieldsActiveRR",
	"/ManageNAW",
	"/ManageRoad",
	"/AddRoad",
	"/AddRoadRate",
	"/AddPerson",
	"/AddCarTracker",
	"/CarTrackerList",
	"/NawList",
	"/PersonalData",
	"/ChangeDateout",
	"/ChangeLastname",
	"/ChangeAddress",
	"/ChangeNumber",
	"/ChangeZipcode",
	"/ChangeCity",
	"/ChangeTelephone",
	"/ChangeMail",
	"/ChangeCT",
	"/ChangeMA",
	"/CarBrandChange",
	"/CarModelChange",
	"/LicenseChange",
	"/PrizeCategoryChange",
	"/runBatchJob",
	"/SearchNAW",
	"/SearchCT"
})
public class DBServlet extends HttpServlet {

	@Inject
	INAWService ns;

	@Inject
	ICarTrackerService cts;

	@Inject
	ICarOwnerService cos;

	@Inject
	IRoadService rs;

	@Inject
	IRoadRateService rrs;

	@Inject
	IInvoiceService iis;
	private int bsn;
	private String id;

	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		String userPath = req.getServletPath();

		switch (userPath) {
			case "/Manage": {
				req.setAttribute("naws", ns.getAllNaws());
				req.setAttribute("countnaws", ns.getAllNaws().size());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
				view.forward(req, res);
				break;
			}
			case "/ManageRoadRate": {
				req.setAttribute("roads", rs.getAllRoads());
				req.setAttribute("countroads", rs.getAllRoads().size());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manageroadrate.jsp");
				view.forward(req, res);
				break;

			}
			case "/ManageCartracker": {
				req.setAttribute("cartrackers", cts.getAllCarTrackers());
				req.setAttribute("countcartrackers", cts.getAllCarTrackers().size());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/managecartracker.jsp");
				view.forward(req, res);
				break;
			}
			case "/runBatchJob": {
				JobOperator jobOperator = BatchRuntime.getJobOperator();
				Properties props = new Properties();

				props.put("year", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
				props.put("month", Integer.toString(Calendar.getInstance().get(Calendar.MONTH)));
				long execID = jobOperator.start("invoiceBatchJob", props);
			}

		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		String userPath = req.getServletPath();

		if (userPath.equals("/Manage")) {
			String json = null;
			String OptionBSN = req.getParameter("OptionBSN").trim();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW fix = ns.getNAWByBsn(Integer.parseInt(OptionBSN));
			if (OptionBSN != null) {
				jsonMap.put("bsn", OptionBSN);
				jsonMap.put("firstname", fix.getFirstname());
				jsonMap.put("lastname", fix.getLastname());
				jsonMap.put("address", fix.getAddress());
				jsonMap.put("housenumber", fix.getNumber());
				jsonMap.put("zipcode", fix.getZipcode());
				jsonMap.put("city", fix.getCity());
				jsonMap.put("telephone", fix.getTelephone());
				jsonMap.put("email", fix.getEmail());

				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de json: " + json.toString());
				res.setContentType("application/json");
				req.setAttribute("countcartrackers", cts.getCarTrackerById(fix).size());
				List<CarTracker> lijst = cts.getCarTrackerById(fix);
				req.setAttribute("cartrackers", lijst);
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);

			}

		}
		if (userPath.equals("/ManageNAW")) {
			String json = null;
			String licenseplateReceive = req.getParameter("OptionKenteken").trim();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			CarTracker getCT;
			CarOwner getOwner;
			NAW persoon;
			getCT = cts.getCarTrackerByLicensePlate(licenseplateReceive);
			getOwner = cos.getCarOwnerByCarTracker(getCT);
			persoon = getOwner.getNawid();
			if (licenseplateReceive != null) {
				jsonMap.put("bsn", Integer.toString(persoon.getBsn()));
				jsonMap.put("firstname", persoon.getFirstname());
				jsonMap.put("lastname", persoon.getLastname());
				jsonMap.put("address", persoon.getAddress());
				jsonMap.put("housenumber", persoon.getNumber());
				jsonMap.put("zipcode", persoon.getZipcode());
				jsonMap.put("city", persoon.getCity());
				jsonMap.put("telephone", persoon.getTelephone());
				jsonMap.put("email", persoon.getEmail());

				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de json: " + json.toString());
				res.setContentType("application/json");
				req.setAttribute("countcartrackers", cts.getCarTrackerById(persoon).size());
				List<CarTracker> lijst = cts.getCarTrackerById(persoon);
				req.setAttribute("cartrackers", lijst);
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);

			}

		}
		if (userPath.equals("/ManageRoad")) {

			String json = null;
			String roadnameReceive = req.getParameter("OptionRoad").trim();
			String rrID = req.getParameter("OptionRR");
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			//Date parsedDate = dateFormat.parse(rrDatum);
			//Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			Road weg;
			List<RoadRate> km;
			weg = rs.getRoad(roadnameReceive);
			km = rrs.getRoadRatesByName(weg);
			RoadRate goodrr = null;

			for (RoadRate rr : km) {
				if (rr.getId() == Long.parseLong(rrID)) {
					goodrr = rr;
				}
			}

			jsonMap.put("naam", goodrr.getRoad().getId());
			jsonMap.put("date_in", goodrr.getTimestamp_in().toString());
			if (goodrr.getTimestamp_out() != null) {
				jsonMap.put("dateout", goodrr.getTimestamp_out().toString());
			} else {
				jsonMap.put("dateout", "");
			}
			jsonMap.put("timestart", goodrr.getTime_start().toString());
			jsonMap.put("timeend", goodrr.getTime_end().toString());
			jsonMap.put("rate", String.valueOf(goodrr.getRate()));
			json = new Gson().toJson(jsonMap, Map.class);
			System.out.println("Dit is de json: " + json.toString());
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);

		}
		if (userPath.equals("/FillCT")) {
			String json = null;
			String OptionBSN = req.getParameter("OptionBSN").trim();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW fix = ns.getNAWByBsn(Integer.parseInt(OptionBSN));
			List<CarTracker> cartrackers = cts.getCarTrackerById(fix);

			JSONObject container = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (CarTracker ct : cartrackers) {
				JSONObject js = new JSONObject();
				js.put("priceCategory", ct.getPriceCategory());
				js.put("licensePlate", ct.getLicensePlate());
				js.put("modelCar", ct.getModelCar());
				js.put("brandCar", ct.getBrandCar());
				js.put("websiteSubscription", ct.isRekeningrijdersWebsite());
				jsonArray.add(js);
			}
			container.put("cartrackers", jsonArray);
			System.out.println("Dit is de CT json: " + container.toJSONString());
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(container.toJSONString());
		}
		if (userPath.equals("/FillFieldsCT")) {
			String json = null;
			String Kenteken = req.getParameter("OptionBSN").trim();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			CarTracker ct = cts.getCarTrackerByLicensePlate(Kenteken);
			String price = ct.getPriceCategory();

			if (Kenteken != null) {
				jsonMap.put("pricecategory", price);
				jsonMap.put("licenseplate", ct.getLicensePlate());
				jsonMap.put("modelcar", ct.getModelCar());
				jsonMap.put("brandcar", ct.getBrandCar());

				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);

			}
		}
		if (userPath.equals("/FillFieldsRR")) {
			String json = null;
			String roadname = req.getParameter("OptionRR").trim();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			Road r = rs.getRoad(roadname);
			List<RoadRate> roadrates = rrs.getRoadRatesByName(r);

			JSONObject container = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (RoadRate rr : roadrates) {
				JSONObject js = new JSONObject();
				js.put("id", rr.getId().toString());
				js.put("datein", rr.getTimestamp_in().toString());
				if (rr.getTimestamp_out() != null) {
					js.put("dateout", rr.getTimestamp_out().toString());
				} else {
					js.put("dateout", "");
				}
				js.put("timestart", rr.getTime_start().toString());
				js.put("timeend", rr.getTime_end().toString());
				jsonArray.add(js);
			}
			container.put("roadrates", jsonArray);
			System.out.println("Dit is de RR json: " + container.toJSONString());
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(container.toJSONString());
		}
		if (userPath.equals("/FillFieldsActiveRR")) {
			String json = null;
			String roadname = req.getParameter("OptionRR").trim();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			Road r = rs.getRoad(roadname);
			List<RoadRate> roadrates = rrs.getRoadRatesByName(r);
			Date now = new Date();
			JSONObject container = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (RoadRate rr : roadrates) {
				JSONObject js = new JSONObject();
				if (rr.getTimestamp_out() != null) {

					if (rr.getTimestamp_out().after(now)) {
						js.put("id", rr.getId().toString());
						js.put("datein", rr.getTimestamp_in().toString());
						js.put("dateout", rr.getTimestamp_out().toString());
						js.put("timestart", rr.getTime_start().toString());
						js.put("timeend", rr.getTime_end().toString());
						jsonArray.add(js);

					}
				} else {
					js.put("id", rr.getId().toString());
					js.put("datein", rr.getTimestamp_in().toString());
					js.put("dateout", "");
					js.put("timestart", rr.getTime_start().toString());
					js.put("timeend", rr.getTime_end().toString());
					jsonArray.add(js);
				}

			}
			container.put("roadrates", jsonArray);
			System.out.println("Dit is de RR json: " + container.toJSONString());
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(container.toJSONString());
		}
		if (userPath.equals("/ChangeDateout")) {
			String json = null;
			String newdatestr = req.getParameter("OutDate").toString();
			String olddate = req.getParameter("InDate").toString();
			String newroad = req.getParameter("Road").toString();
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			Road inc = rs.getRoad(newroad);
			List<RoadRate> edit = rrs.getRoadRatesByName(inc);
			for (RoadRate rr : edit) {
				if (rr.getTimestamp_in().toString().equals(olddate)) {
					rrs.AddDateOut(rr, Timestamp.valueOf(newdatestr));
				}
			}
			jsonMap.put("dateout", newdatestr);
			json = new Gson().toJson(jsonMap, Map.class);
			System.out.println("Dit is de veranderde json: " + json.toString());
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);

		}
		if (userPath.equals(
			"/ChangeFirstname")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newfirstname = req.getParameter("NewFirstname");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeFirstname(incoming, newfirstname);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("firstname", incoming.getFirstname());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeLastname")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newlastname = req.getParameter("NewLastname");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeLastname(incoming, newlastname);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("lastname", incoming.getLastname());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeAddress")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newstreet = req.getParameter("NewAddress");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeAddress(incoming, newstreet);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("address", incoming.getAddress());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeNumber")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newnumber = req.getParameter("NewNumber");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeHouseNumber(incoming, newnumber);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("housenumber", incoming.getNumber());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeZipcode")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newzipcode = req.getParameter("NewZipcode");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeZipcode(incoming, newzipcode);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("zipcode", incoming.getZipcode());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeCity")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newcity = req.getParameter("NewCity");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeCity(incoming, newcity);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("city", incoming.getCity());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeTelephone")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newtelephone = req.getParameter("NewTelephone");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeTelephone(incoming, newtelephone);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("telephone", incoming.getTelephone());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/ChangeMail")) {
			String json = null;
			String newbsn = req.getParameter("BSN").trim();
			String newmail = req.getParameter("NewMail");
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			NAW incoming = ns.getNAWByBsn(Integer.parseInt(newbsn));
			incoming = ns.changeEmail(incoming, newmail);
			if (incoming != null) {
				jsonMap.put("bsn", newbsn);
				jsonMap.put("mail", incoming.getEmail());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}

		if (userPath.equals(
			"/AddPerson")) {
			int bsn = Integer.parseInt(req.getParameter("bsn"));
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String address = req.getParameter("address");
			String number = req.getParameter("number");
			String zipcode = req.getParameter("zipcode");
			String city = req.getParameter("city");
			String telephone = req.getParameter("telephone");
			String email = req.getParameter("email");
			NAW n = new NAW(bsn, firstname, lastname, address, number, zipcode, city, telephone, email);
			ns.createNAW(n);
			req.setAttribute("naws", ns.getAllNaws());
			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
			view.forward(req, res);
		}
		if (userPath.equals("/AddRoad")) {
			String name = req.getParameter("roadname");
			Road r = new Road(name);

			rs.createRoad(r);
			req.setAttribute("roads", rs.getAllRoads());
			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manageroadrate.jsp");
			view.forward(req, res);
		}

		if (userPath.equals(
			"/AddCarTracker")) {
			req.setAttribute("naws", ns.getAllNaws());
			bsn = Integer.parseInt(req.getParameter("bsnhide"));
			NAW naw = ns.getNAWByBsn(bsn);
			String category = req.getParameter("category");
			String license = req.getParameter("license");
			String carmodel = req.getParameter("carmodel");
			String carbrand = req.getParameter("carbrand");
			CarTracker ct = new CarTracker(category, license, carmodel, carbrand, true);
			cts.createCarTracker(ct);
			req.setAttribute("CTs", cts.getAllCarTrackers());
			Date startdate = new Date();
			CarOwner co = new CarOwner(ct, naw, startdate);
			cos.createCarOwner(co);

			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
			view.forward(req, res);
		}
		if (userPath.equals("/AddRoadRate")) {
			String roadname = req.getParameter("roadname");
			String datein = req.getParameter("datein");
			String dateend = req.getParameter("dateend");
			String starttime = req.getParameter("starttime");
			String endtime = req.getParameter("endtime");
			String tarief = req.getParameter("rate");
			Road r = rs.getRoad(roadname);

			String[] startdateArray = datein.split("-");
			String[] starttimeArray = starttime.split(":");
			String[] endtimeArray = endtime.split(":");
			if (!dateend.isEmpty()) {
				String[] enddateArray = dateend.split("-");

				try {
					int startday = Integer.parseInt(startdateArray[0]);
					int startmonth = Integer.parseInt(startdateArray[1]) - 1;
					int starthours = Integer.parseInt(starttimeArray[0]);
					int startminutes = Integer.parseInt(starttimeArray[1]);
					int startseconds = 00;

					int endday = Integer.parseInt(enddateArray[0]);
					int endmonth = Integer.parseInt(enddateArray[1]) - 1;
					int endhours = Integer.parseInt(endtimeArray[0]);
					int endminutes = Integer.parseInt(endtimeArray[1]);
					int endseconds = 00;

					//parseInt is bugged and transforms 2016 into 3916
					int year = Integer.parseInt(startdateArray[2]) - 1900;
					Date datein_date = new Date(year, startmonth, startday);
					System.out.println(datein_date.toString());
					Date starttime_date = new Date(year, startmonth, startday, starthours, startminutes, startseconds);
					Date dateend_date = new Date(year, endmonth, endday);
					Date endtime_date = new Date(year, endmonth, endday, endhours, endminutes, endseconds);
					Double rate = Double.parseDouble(tarief);
					RoadRate rr = new RoadRate(r, datein_date, dateend_date, starttime_date, endtime_date, rate);
					System.out.println(rr.toString());
					rrs.createRoadRate(rr);
					RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
					view.forward(req, res);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			} else {
				try {
					int startday = Integer.parseInt(startdateArray[0]);
					int startmonth = Integer.parseInt(startdateArray[1]) - 1;
					int starthours = Integer.parseInt(starttimeArray[0]);
					int startminutes = Integer.parseInt(starttimeArray[1]);
					int startseconds = 00;
					int endhours = Integer.parseInt(endtimeArray[0]);
					int endminutes = Integer.parseInt(endtimeArray[1]);
					int endseconds = 00;

					//parseInt is bugged and transforms 2016 into 3916
					int year = Integer.parseInt(startdateArray[2]) - 1900;
					Date datein_date = new Date(year, startmonth, startday);
					System.out.println(datein_date.toString());
					Date starttime_date = new Date(year, startmonth, startday, starthours, startminutes, startseconds);
					Date dateend_date = null;
					Date endtime_date = new Date(year, startmonth, startday, endhours, endminutes, endseconds);
					Double rate = Double.parseDouble(tarief);
					RoadRate rr = new RoadRate(r, datein_date, dateend_date, starttime_date, endtime_date, rate);
					System.out.println(rr.toString());
					rrs.createRoadRate(rr);
					RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
					view.forward(req, res);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}

		}
		if (userPath.equals(
			"/PersonalData")) {
			bsn = Integer.parseInt(req.getParameter("bsn"));

			NAW naw = ns.getNAWByBsn(bsn);
			ArrayList<CarTracker> ctList = cts.getCarTrackerById(naw);

			req.setAttribute("theCTs", ctList);
			req.setAttribute("theUser", naw);

			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
			view.forward(req, res);
		}

		if (userPath.equals(
			"/ChangeCT")) {
			long cid = Long.parseLong(req.getParameter("id"));

			CarTracker ct = cts.getSingleCarTrackerById(cid);

			req.setAttribute("ct", ct);
			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/changecartracker.jsp");
			view.forward(req, res);

		}

		if (userPath.equals(
			"/PrizeCategoryChange")) {
			String category = req.getParameter("category");

			NAW naw = ns.getNAWByBsn(bsn);
			CarTracker ct = cts.getSingleCarTrackerByNaw(naw);

			req.setAttribute("theUser", ct);

			cts.changePrizeCategory(ct, category);

			req.setAttribute("theCT", ct);
			req.setAttribute("theUser", naw);
			ArrayList<CarTracker> ctList = cts.getCarTrackerById(naw);
			req.setAttribute("theCTs", ctList);

			RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
			viewResult.forward(req, res);
		}

		if (userPath.equals(
			"/LicenseChange")) {
			String license = req.getParameter("license");

			NAW naw = ns.getNAWByBsn(bsn);
			CarTracker ct = cts.getSingleCarTrackerByNaw(naw);
			req.setAttribute("theUser", ct);
			cts.changeLicense(ct, license);

			req.setAttribute("theCT", ct);
			req.setAttribute("theUser", naw);
			ArrayList<CarTracker> ctList = cts.getCarTrackerById(naw);
			req.setAttribute("theCTs", ctList);

			RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
			viewResult.forward(req, res);
		}

		if (userPath.equals(
			"/CarModelChange")) {
			String carmodel = req.getParameter("carmodel");

			NAW naw = ns.getNAWByBsn(bsn);
			CarTracker ct = cts.getSingleCarTrackerByNaw(naw);
			cts.changeModelCar(ct, carmodel);

			req.setAttribute("theCT", ct);
			req.setAttribute("theUser", naw);
			ArrayList<CarTracker> ctList = cts.getCarTrackerById(naw);
			req.setAttribute("theCTs", ctList);

			RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
			viewResult.forward(req, res);
		}

		if (userPath.equals(
			"/CarBrandChange")) {
			String carbrand = req.getParameter("carbrand");

			NAW naw = ns.getNAWByBsn(bsn);
			CarTracker ct = cts.getSingleCarTrackerByNaw(naw);
			cts.changeBrandCar(ct, carbrand);

			req.setAttribute("theCT", ct);
			req.setAttribute("theUser", naw);
			ArrayList<CarTracker> ctList = cts.getCarTrackerById(naw);
			req.setAttribute("theCTs", ctList);

			RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
			viewResult.forward(req, res);
		}
		if (userPath.equals("/SearchNAW")) {
			String searchNAW = req.getParameter("NAW");

			NAW getNAW = ns.getNAWByBsn(Integer.parseInt(searchNAW));

			String json = null;
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();

			if (getNAW != null) {
				jsonMap.put("bsn", searchNAW);
				jsonMap.put("firstname", getNAW.getFirstname());
				jsonMap.put("lastname", getNAW.getLastname());
				jsonMap.put("address", getNAW.getAddress());
				jsonMap.put("housenumber", getNAW.getNumber());
				jsonMap.put("zipcode", getNAW.getZipcode());
				jsonMap.put("city", getNAW.getCity());
				jsonMap.put("telephone", getNAW.getTelephone());
				jsonMap.put("email", getNAW.getEmail());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			} else {
				//error
			}

		}
		if (userPath.equals("/SearchCT")) {
			String searchCT = req.getParameter("CT");

			CarTracker getCT = cts.getCarTrackerByLicensePlate(searchCT);

			String json = null;
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();

			if (getCT != null) {
				jsonMap.put("pricecategory", String.valueOf(getCT.getPriceCategory()));
				jsonMap.put("licenseplate", getCT.getLicensePlate());
				jsonMap.put("brandcar", getCT.getBrandCar());
				jsonMap.put("modelcar", getCT.getModelCar());
				json = new Gson().toJson(jsonMap, Map.class);

				System.out.println("Dit is de veranderde json: " + json.toString());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			} else {
				//error
			}

		}
		if (userPath.equals("/Error")) {
			URL url = new URL("http://145.93.165.43:9233/MonitoringSysteem/Rest/statusmessages/postmessage");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			//Dit is het object zelf
			String input = null;
			Map<String, String> jsonMap = new LinkedHashMap<String, String>();
			jsonMap.put("systeemnaam", "RekeningAdministratieOverheid");
			jsonMap.put("message", "RekeningAdministratieOverheid is down");
			input = new Gson().toJson(jsonMap, Map.class).toString();

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		}

	}

	public boolean checkIfOnlineUsingAJAX(String channel) throws IOException, JSONException {
		String channelURL = "https://api.twitch.tv/kraken/streams/" + channel;

		String jsonText = readFromUrl(channelURL);// reads text from URL
		org.json.JSONObject json = new org.json.JSONObject(jsonText);

		if (json.isNull("stream")) {
			System.out.println(channel + " is not live at this moment.");
			return false;
		} else {
			org.json.JSONObject online = new org.json.JSONObject(json.get("stream").toString());
			System.out.println(online.get("game").toString());
			int maxlength = online.getString("created_at").length() - 1;
			String timestart = online.getString("created_at").substring(11, maxlength);
			String game = online.getString("game");
			String viewercount = Integer.toString(online.getInt("viewers"));
			System.out.println(channel + " is live since: " + timestart);
			System.out.println(channel + " is playing " + game + " at the moment.");
			System.out.println("Stream has " + viewercount + " viewers at this moment.");
			return true;
		}

	}

	private static String readFromUrl(String url) throws MalformedURLException, IOException {
		URL page = new URL(url);
		try (Stream<String> stream = new BufferedReader(new InputStreamReader(
			page.openStream(), StandardCharsets.UTF_8)).lines()) {
			return stream.collect(Collectors.joining(System.lineSeparator()));
		}

	}

}
