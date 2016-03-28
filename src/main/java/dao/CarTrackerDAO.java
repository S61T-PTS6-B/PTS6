/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 *//**
 *
 * @author koenv
 *//**
 *
 * @author koenv
 */
public interface CarTrackerDAO {
    public void createCarTracker(CarTracker ct);
    
    public List<CarTracker> getAllCarTrackers();

    public ArrayList<CarTracker> getCarTrackerByNaw(NAW naw);
    
    public void changeOwner(CarTracker ct, NAW naw);
    
    public void changePrizeCategory(CarTracker ct, double prizecategory);
    
    public void changeLicensePlate(CarTracker ct, String licenseplate);
    
    public void changeModelCar(CarTracker ct, String modelcar);
    
    public void changeBrandCar(CarTracker ct, String brandcar);
    
    public void changeWebsiteSubscription(CarTracker ct, Boolean subscription);

    public CarTracker getSingleCarTrackerByNaw(NAW naw);

    public CarTracker getSingleCarTrackerById(long id);
}
