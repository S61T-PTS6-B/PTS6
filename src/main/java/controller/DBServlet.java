/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import model.CarOwner;
import model.CarTracker;
import model.NAW;
import service.ICarOwnerService;
import service.ICarTrackerService;
import service.INAWService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author koenv
 */
@WebServlet(name = "CarTrackerAdm", urlPatterns = {"/CarTrackerAdm",
	"/Manage",
	"/FillCT",
	"/FillFieldsCT",
	"/ManageCartracker",
	"/ManageNAW",
	"/AddMileage",
	"/AddPerson",
	"/AddCarTracker",
	"/CarTrackerList",
	"/NawList",
	"/PersonalData",
	"/ChangeFirstname",
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
	"/PrizeCategoryChange"
})
public class DBServlet extends HttpServlet {

	@Inject
	INAWService ns;

	@Inject
	ICarTrackerService cts;

	@Inject
	ICarOwnerService cos;

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
			case "/ManageCartracker": {
				req.setAttribute("cartrackers", cts.getAllCarTrackers());
				req.setAttribute("countcartrackers", cts.getAllCarTrackers().size());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/managecartracker.jsp");
				view.forward(req, res);
				break;
			}
			case "/AddPerson": {
				req.setAttribute("naws", ns.getAllNaws());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
				view.forward(req, res);
				break;
			}
			case "/CarTrackerList": {
				req.setAttribute("CTs", cts.getAllCarTrackers());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/cartrackerlist.jsp");
				view.forward(req, res);
				break;
			}
			case "/NawList": {
				req.setAttribute("naws", ns.getAllNaws());
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/NawList.jsp");
				view.forward(req, res);
				break;
			}
			case "/ChangeCT": {
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/changecartracker.jsp");
				view.forward(req, res);
				break;
			}
			case "/ChangeMA": {
				RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/changemileage.jsp");
				view.forward(req, res);
				break;
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
			double price = ct.getPriceCategory();

			if (Kenteken != null) {
				jsonMap.put("pricecategory", Double.toString(price));
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

		if (userPath.equals(
			"/AddCarTracker")) {
			req.setAttribute("naws", ns.getAllNaws());
			bsn = Integer.parseInt(req.getParameter("bsnhide"));
			NAW naw = ns.getNAWByBsn(bsn);
			double category = Double.parseDouble(req.getParameter("category"));
			String license = req.getParameter("license");
			String carmodel = req.getParameter("carmodel");
			String carbrand = req.getParameter("carbrand");
			CarTracker ct = new CarTracker(category, license, carmodel, carbrand, true);
			cts.createCarTracker(ct);
			req.setAttribute("CTs", cts.getAllCarTrackers());
			CarOwner co = new CarOwner(ct, naw);
			cos.createCarOwner(co);

			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
			view.forward(req, res);
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
			double category = Double.parseDouble(req.getParameter("category"));

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
	}

}
