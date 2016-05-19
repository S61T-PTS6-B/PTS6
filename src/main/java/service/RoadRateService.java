/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RoadRateDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import model.RoadRate;

/**
 *
 * @author koenv
 */
public class RoadRateService implements IRoadRateService {

	@EJB
	RoadRateDAO rrd;
	
	@Override
	public void createRoadRate(RoadRate rr) {
		rrd.createRoadRate(rr);
	}

	@Override
	public List<RoadRate> getAllRoadRates() {
		return rrd.getAllRoadRates();
	}

	@Override
	public List<RoadRate> getRoadRatesByBeginTime(Date timestart) {
		return rrd.getRoadRatesByBeginTime(timestart);
	}

	@Override
	public List<RoadRate> getRoadRatesByEndTime(Date timeend) {
		return rrd.getRoadRatesByEndTime(timeend);
	}

	@Override
	public List<RoadRate> getRoadRatesByName(String name) {
		return rrd.getRoadRatesByName(name);
	}

}
