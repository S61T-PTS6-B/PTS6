/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CarTrackerDAO;
import dao.NawDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.NAW;


/**
 *
 * @author koenv
 */
@Stateless
public class NAWService {
    @EJB
    NawDAO nd;
    
    public void createNAW(NAW naw) {
        nd.createNaw(naw);
    }
    public List<NAW> getAllNaws(){
        return nd.getAllNaws();
    }
}
