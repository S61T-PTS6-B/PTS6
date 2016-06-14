/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CarOwner;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface CarOwnerDAO {

	public void createCarOwner(CarOwner co);

	public CarOwner getCarOwnerByNawId(NAW id);
	
	public List<CarTracker> getCarHistory(CarOwner co);

	public CarOwner getCarOwnerByCarTrackerId(CarTracker id);

	public void setEndownership(CarOwner co);
	public List<CarOwner> getActiveCarOwners();
}
