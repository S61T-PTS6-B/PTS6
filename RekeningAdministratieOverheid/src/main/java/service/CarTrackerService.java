/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CarTrackerDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.CarTracker;

/**
 *
 * @author koenv
 */
@Stateless
public class CarTrackerService {
    @EJB
    CarTrackerDAO ctd;
    
    public List<CarTracker> getAllCarTrackers(){
        return ctd.getAllCarTrackers();
    }
    
}
