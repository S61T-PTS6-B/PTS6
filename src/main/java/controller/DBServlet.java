/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import model.CarOwner;
import model.CarTracker;
import model.MileageRate;
import model.NAW;
import service.ICarOwnerService;
import service.ICarTrackerService;
import service.IMileageRateService;
import service.INAWService;

/**
 *
 * @author koenv
 */
@WebServlet(name = "CarTrackerAdm", urlPatterns = {"/CarTrackerAdm",
    "/Manage",
    "/ManageMileage",
    "/AddMileage",
    "/AddPerson",
    "/AddCarTracker",
    "/CarTrackerList",
    "/NawList",
    "/MileageList",
    "/PersonalData",
    "/ChangeCT",
    "/ChangeMA",
    "/CarBrandChange",
    "/CarModelChange",
    "/LicenseChange",
    "/PrizeCategoryChange",
    "/MileageChange",
    "/RegioChange",
    "/CategoryChange",
    "/IntervalChange"})
public class DBServlet extends HttpServlet {

    @Inject
    INAWService ns;

    @Inject
    ICarTrackerService cts;

    @Inject
    ICarOwnerService cos;

    @Inject
    IMileageRateService mrs;

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
                RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/manage.jsp");
                view.forward(req, res);
                break;
            }
            case "/ManageMileage": {
                RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/managemilage.jsp");
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
            case "/MileageList": {
                List<MileageRate> mars = mrs.getAllRates();
                req.setAttribute("MAR", mars);
                RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/mileagelist.jsp");
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
        if (userPath.equals("/AddMileage")) {
            double rate = Double.parseDouble(req.getParameter("mar"));
            String regio = req.getParameter("regio");
            double category = Double.parseDouble(req.getParameter("pricecategory"));
            double interval = Double.parseDouble(req.getParameter("interval"));

            MileageRate mar = new MileageRate(rate, regio, category, interval);
            mrs.createMileageRate(mar);
            
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/managemilage.jsp");
                view.forward(req, res);

        }
        if (userPath.equals("/AddPerson")) {
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
        if (userPath.equals("/AddCarTracker")) {
            req.setAttribute("naws", ns.getAllNaws());
            bsn = Integer.parseInt(req.getParameter("bsn"));
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

            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/cartrackerlist.jsp");
            view.forward(req, res);
        }
        if (userPath.equals("/PersonalData")) {
            bsn = Integer.parseInt(req.getParameter("bsn"));

            NAW naw = ns.getNAWByBsn(bsn);
            ArrayList<CarTracker> ctList = cts.getCarTrackerById(naw);

            req.setAttribute("theCTs", ctList);
            req.setAttribute("theUser", naw);

            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            view.forward(req, res);
        }
        if (userPath.equals("/ChangeCT")) {
            long cid = Long.parseLong(req.getParameter("id"));

            CarTracker ct = cts.getSingleCarTrackerById(cid);

            req.setAttribute("ct", ct);
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/changecartracker.jsp");
            view.forward(req, res);

        }
        if (userPath.equals("/ChangeMA")) {
            String id = req.getParameter("id");
            
            MileageRate mr = mrs.getRateById(id);
            
            req.setAttribute("mar", mr);
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/changemileage.jsp");
            view.forward(req, res);
        }
        if (userPath.equals("/PrizeCategoryChange")) {
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
        if (userPath.equals("/LicenseChange")) {
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
        if (userPath.equals("/CarModelChange")) {
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
        if (userPath.equals("/CarBrandChange")) {
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
        if (userPath.equals("/MileageChange")) {
            String id = req.getParameter("id");
            String mileageRate = req.getParameter("mileagerate");
            
            MileageRate mr = mrs.getRateById(id);
            mrs.changeMileageRate(mr, Double.parseDouble(mileageRate));
            
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req, res);
            
            
        }
        if (userPath.equals("/RegioChange")) {
            String id = req.getParameter("id");
            String regio = req.getParameter("regio");
            
            MileageRate mr = mrs.getRateById(id);
            mrs.changeRegio(mr, regio);
            req.setAttribute("mar", mr);
            
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/changemileage.jsp");
            viewResult.forward(req, res);  
        }
        if (userPath.equals("/CategoryChange")) {
            String id = req.getParameter("id");
            String pricecategory = req.getParameter("pricecategory");
            
            MileageRate mr = mrs.getRateById(id);
            mrs.changePrizeCategory(mr, Double.parseDouble(pricecategory));
            
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req,res);
        }
        if (userPath.equals("/IntervalChange")) {
            String id = req.getParameter("id");
            String interval = req.getParameter("interval");
            
            MileageRate mr = mrs.getRateById(id);
            mrs.changeInterval(mr, Double.parseDouble(interval));
            
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req,res);
        }
    }

}
