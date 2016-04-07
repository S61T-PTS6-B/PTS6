/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import model.CarOwner;
import model.CarTracker;
import model.NAW;
import service.CarOwnerService;
import service.CarTrackerService;
import service.NAWService;

/**
 *
 * @author koenv
 */
@WebServlet(name = "CarTrackerAdm", urlPatterns = {"/CarTrackerAdm",
    "/Manage",
    "/AddPerson",
    "/AddCarTracker",
    "/CarTrackerList",
    "/NawList",
    "/PersonalData",
    "/ChangeCT",
    "/CarBrandChange",
    "/CarModelChange",
    "/LicenseChange",
    "/PrizeCategoryChange"})
public class DBServlet extends HttpServlet {

    @EJB
    NAWService ns;

    @EJB
    CarTrackerService cts;

    @EJB
    CarOwnerService cos;

    private int bsn;

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

        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String userPath = req.getServletPath();
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
    }

}
