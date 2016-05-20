/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Road;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class RoadDAOImp implements RoadDAO {

	@PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
	EntityManager em;

	@Override
	public void createRoad(Road road) {
		em.persist(road);
	}

	@Override
	public List<Road> getAllRoads() {
		return (List<Road>) em.createQuery("SELECT r FROM Road r").getResultList();
	}

	@Override
	public Road getRoad(String name) {
		Road road = em.find(Road.class, name);
		return road;
	}
}
