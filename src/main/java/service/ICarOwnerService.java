/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.CarOwner;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
public interface ICarOwnerService {

	public void createCarOwner(CarOwner c);

	public CarOwner getCarOwnerByNawId(NAW id);

	public CarOwner getCarOwnerByCarTracker(CarTracker id);

}
