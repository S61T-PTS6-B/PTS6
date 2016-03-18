/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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

    public CarTracker getCarTrackerByNaw(NAW naw);
}
