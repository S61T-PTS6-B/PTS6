/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
                                                   "/CarTrackerList", 
                                                   "/NawList", 
                                                   "/PersonalData", 
                                                   "/ChangeCT", 
                                                   "/CarBrandChange", 
                                                   "/CarModelChange", 
                                                   "/LicenseChange", 
                                                   "/PrizeCategoryChange"})
public class DBServlet extends HttpServlet{
    @EJB
    NAWService ns;
    
    @EJB
    CarTrackerService cts;
    
    @EJB
    CarOwnerService cos;
    
    private String id;
    

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)  
 throws ServletException, IOException {  
            
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        String userPath = req.getServletPath();

        switch (userPath) {
            case "/CarTrackerAdm": {
                RequestDispatcher view = req.getRequestDispatcher("index.jsp");
                view.forward(req, res);
                break;
            }
            case "/Index": {
                RequestDispatcher view = req.getRequestDispatcher("index.jsp");
                view.forward(req, res);
                break;
            }
            case "/": {
                RequestDispatcher view = req.getRequestDispatcher("index.jsp");
                view.forward(req, res);
                break;
            }
            case "/CarTrackerList": {
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
        if (userPath.equals("/CarTrackerAdm")) {

            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String address = req.getParameter("address");
            String number = req.getParameter("number");
            String zipcode = req.getParameter("zipcode");
            String city = req.getParameter("city");
            String telephone = req.getParameter("telephone");
            NAW n = new NAW(firstname, lastname, address, number, zipcode, city, telephone);
            ns.createNAW(n);  
            
            double category = Double.parseDouble(req.getParameter("category"));
            String license = req.getParameter("license");
            String carmodel = req.getParameter("carmodel");
            String carbrand = req.getParameter("carbrand");
            CarTracker ct = new CarTracker(category, license, carmodel, carbrand, true);
            cts.createCarTracker(ct);
            
            CarOwner co = new CarOwner(ct, n);
            cos.createCarOwner(co);
            
        }
        if (userPath.equals("/PersonalData"))
        {
                id = req.getParameter("id");
                NAW naw = ns.getNAWById(id);
                CarTracker ct = cts.getCarTrackerById(naw);
                req.setAttribute("theUser", ct);
                RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
                view.forward(req, res);
        }
        if (userPath.equals("/PrizeCategoryChange"))
        {
            double category = Double.parseDouble(req.getParameter("category"));
            NAW naw = ns.getNAWById(id);
            CarTracker ct = cts.getCarTrackerById(naw);
            req.setAttribute("theUser", ct); 
            ct.setTariefCategorie(category);
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req, res); 
        }
        if (userPath.equals("/LicenseChange"))
        {
            String license = req.getParameter("license");

            NAW naw = ns.getNAWById(id);
            CarTracker ct = cts.getCarTrackerById(naw);
            req.setAttribute("theUser", ct); 
            ct.setKenteken(license);
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req, res); 
        }
        if (userPath.equals("/CarModelChange"))
        {
            String carmodel = req.getParameter("carmodel");

            NAW naw = ns.getNAWById(id);
            CarTracker ct = cts.getCarTrackerById(naw);
            req.setAttribute("theUser", ct); 
            ct.setModelAuto(carmodel);
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req, res);  
        }
        if (userPath.equals("/CarBrandChange"))
        {
            NAW naw = ns.getNAWById(id);
            CarTracker ct = cts.getCarTrackerById(naw);
            req.setAttribute("theUser", ct); 
            String carbrand = req.getParameter("carbrand");
            cts.changeBrandCar(ct, carbrand);
            req.setAttribute("theUser", ct);
            RequestDispatcher viewResult = req.getRequestDispatcher("/WEB-INF/pages/personaldata.jsp");
            viewResult.forward(req, res);  
            
        }

        
    }
    
}
