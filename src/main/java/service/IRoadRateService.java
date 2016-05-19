/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import model.RoadRate;

/**
 *
 * @author koenv
 */
public interface IRoadRateService {

	public void createRoadRate(RoadRate rr);

	public List<RoadRate> getAllRoadRates();

	public List<RoadRate> getRoadRatesByBeginTime(Date timestart);

	public List<RoadRate> getRoadRatesByEndTime(Date timeend);

	public List<RoadRate> getRoadRatesByName(String name);
        
        public double getRoadRateByDate(String roadName, Date date);
}
