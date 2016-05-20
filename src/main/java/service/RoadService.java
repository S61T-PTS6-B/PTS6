/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RoadDAO;
import java.util.List;
import model.Road;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author koenv
 */


@Stateless
public class RoadService implements IRoadService {

	@EJB
	RoadDAO rd;

	@Override
	public void createRoad(Road road) {
		rd.createRoad(road);
	}

	@Override
	public List<Road> getAllRoads() {
		return rd.getAllRoads();
	}

	@Override
	public Road getRoad(String name) {
		return rd.getRoad(name);
	}

}
