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
public class CarTrackerService implements ICarTrackerService {

	@EJB
	CarTrackerDAO ctd;

	@Override
	public void createCarTracker(CarTracker c) {
		ctd.createCarTracker(c);
	}

	@Override
	public List<CarTracker> getAllCarTrackers() {
		return ctd.getAllCarTrackers();
	}

	@Override
	public ArrayList<CarTracker> getCarTrackerById(NAW naw) {
		return ctd.getCarTrackerByNaw(naw);
	}

	@Override
	public CarTracker getSingleCarTrackerByNaw(NAW naw) {
		return ctd.getSingleCarTrackerByNaw(naw);
	}

	@Override
	public CarTracker getSingleCarTrackerById(long id) {
		return ctd.getSingleCarTrackerById(id);
	}

	@Override
	public void changeBrandCar(CarTracker ct, String brand) {
		ctd.changeBrandCar(ct, brand);
	}

	@Override
	public void changeModelCar(CarTracker ct, String model) {
		ctd.changeModelCar(ct, model);
	}

	@Override
	public void changeLicense(CarTracker ct, String license) {
		ctd.changeLicensePlate(ct, license);
	}

	@Override
	public void changePrizeCategory(CarTracker ct, double category) {
		ctd.changePrizeCategory(ct, category);
	}

	@Override
	public CarTracker getCarTrackerByLicensePlate(String licenseplateReceive) {
		return ctd.getCarTrackerByLicensePlate(licenseplateReceive);
	}

}
