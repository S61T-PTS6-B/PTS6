/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.RoadRate;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class RoadRateDAOImp implements RoadRateDAO {

	@PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
	EntityManager em;

	@Override
	public void createRoadRate(RoadRate rr) {
		em.persist(rr);
	}

	@Override
	public List<RoadRate> getAllRoadRates() {
		Query query = em.createQuery("SELECT r FROM ROADRATE r");
		return (List<RoadRate>) query.getResultList();
	}

	@Override
	public List<RoadRate> getRoadRatesByBeginTime(Date timestart) {
		List<RoadRate> roads = (List<RoadRate>) em.createQuery("SELECT r FROM ROADRATE r WHERE r.time_start = :time_start").setParameter("time_start", timestart).getResultList();

		return roads;
	}

	@Override
	public List<RoadRate> getRoadRatesByEndTime(Date timeend) {
		List<RoadRate> roads = (List<RoadRate>) em.createQuery("SELECT r FROM ROADRATE r WHERE r.time_end = :time_end").setParameter("time_end", timeend).getResultList();

		return roads;
	}

	@Override
	public List<RoadRate> getRoadRatesByName(String name) {
		List<RoadRate> roads = (List<RoadRate>) em.createQuery("SELECT r FROM ROADRATE r WHERE r.roadname = :roadname").setParameter("roadname", name).getResultList();

		return roads;
	}

}
