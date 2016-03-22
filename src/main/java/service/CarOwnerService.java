/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import dao.CarOwnerDAO;
import model.CarOwner;

/**
 *
 * @author koenv
 */
@Stateless
public class CarOwnerService {
    @EJB
    CarOwnerDAO cod;
    
    public void createCarOwner(CarOwner c) {
        cod.createCarOwner(c);
    }
    
    
}
