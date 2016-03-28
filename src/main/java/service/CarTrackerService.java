/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CarTrackerDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
@Stateless
public class CarTrackerService {
    @EJB
    CarTrackerDAO ctd;
    
    public void createCarTracker(CarTracker c) {
        ctd.createCarTracker(c);
    }
    
    public List<CarTracker> getAllCarTrackers(){
        return ctd.getAllCarTrackers();
    }    

    public ArrayList<CarTracker> getCarTrackerById(NAW naw) {
        return ctd.getCarTrackerByNaw(naw);
    }
    
    public CarTracker getSingleCarTrackerById(NAW naw) {
        return ctd.getSingleCarTrackerByNaw(naw);
    }
    
    public void changeBrandCar(CarTracker ct, String brand)
    {
        ctd.changeBrandCar(ct, brand);
    }
    
    public void changeModelCar(CarTracker ct, String model)
    {
        ctd.changeModelCar(ct, model);
    }
    
    public void changeLicense(CarTracker ct, String license)
    {
        ctd.changeLicensePlate(ct, license);
    }
    
    public void changePrizeCategory(CarTracker ct, double category)
    {
        ctd.changePrizeCategory(ct, category);
    }
    
}
