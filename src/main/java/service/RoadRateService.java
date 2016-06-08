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
import javax.ejb.Stateless;
import model.Road;
import model.RoadRate;

/**
 *
 * @author koenv
 */
@Stateless
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
	public List<RoadRate> getRoadRatesByName(Road name) {
		return rrd.getRoadRatesByName(name);
	}

	@Override
	public double getRoadRateByDate(Road road, Date date) {
		return rrd.getRoadRateByDate(road, date);
	}

	@Override
	public void AddDateOut(RoadRate rr, Date newdate) {
		rrd.AddDateOut(rr, newdate);
	}

}
