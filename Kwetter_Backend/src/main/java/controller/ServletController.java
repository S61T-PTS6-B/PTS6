/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import model.KwetterUser;
import service.KwetterService;

/**
 *
 * @author koenv
 */
@WebServlet("/Index")
public class ServletController extends HttpServlet {
    @EJB
    KwetterService ks;
    
    KwetterUser ku;

    @Transactional
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ku = new KwetterUser();
        ku.setId(1L);
        ku.setUsername("Young Bart");
        ku.setRealname("Bart Kampert");
        ku.setWeb("www.furries.com");
        ku.setBio("Ik heb geen nekbaard maar wil het wel heel graag.");
        ku.setLocation("Furry Convention 2016");
        

        ks.createUser(ku);
        String username = ku.getUsername();
        //ud.find("Young Bart");
        req.setAttribute("theUser", ku);

        req.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    }

}