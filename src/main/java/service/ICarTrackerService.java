/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface ICarTrackerService {

	public void createCarTracker(CarTracker c);

	public List<CarTracker> getAllCarTrackers();

	public ArrayList<CarTracker> getCarTrackerById(NAW naw);

	public CarTracker getSingleCarTrackerByNaw(NAW naw);

	public CarTracker getSingleCarTrackerById(long id);

	public void changeBrandCar(CarTracker ct, String brand);

	public void changeModelCar(CarTracker ct, String model);

	public void changeLicense(CarTracker ct, String license);

	public void changePrizeCategory(CarTracker ct, String category);

	public CarTracker getCarTrackerByLicensePlate(String licenseplateReceive);

	public boolean DatabaseIsOnline();
}
