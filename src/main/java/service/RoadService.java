/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RoadDAO;
import java.util.List;
import javax.ejb.EJB;
import model.Road;

/**
 *
 * @author Max
 */
public class RoadService implements IRoadService {

    @EJB
	RoadDAO rd;
    
    @Override
    public List<Road> getAllRoads() {
        return rd.getAllRoads();
    }
    
}
