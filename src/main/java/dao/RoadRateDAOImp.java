/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Road;
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
		Query query = em.createQuery("SELECT r FROM RoadRate r");
		return (List<RoadRate>) query.getResultList();
	}

	@Override
	public List<RoadRate> getRoadRatesByBeginTime(Date timestart) {
		List<RoadRate> roads = (List<RoadRate>) em.createQuery("SELECT r FROM RoadRate r WHERE r.time_start = :time_start").setParameter("time_start", timestart).getResultList();

		return roads;
	}

	@Override
	public List<RoadRate> getRoadRatesByEndTime(Date timeend) {
		List<RoadRate> roads = (List<RoadRate>) em.createQuery("SELECT r FROM RoadRate r WHERE r.time_end = :time_end").setParameter("time_end", timeend).getResultList();

		return roads;
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	@Override
	public List<RoadRate> getRoadRatesByName(Road name) {
		List<RoadRate> roads = (List<RoadRate>) em.createQuery("SELECT r FROM RoadRate r WHERE r.road = :roadname").setParameter("roadname", name).getResultList();

		return roads;
	}

	@Override
	public double getRoadRateByDate(String roadName, Date date) {
		try {
			SimpleDateFormat dateFormatTimestamp = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dateFormatTime = new SimpleDateFormat("hh/mm");
			double rate = (double) em.createQuery("SELECT r.rate FROM RoadRate r "
				+ "WHERE r.timestamp_in >= :timestamp_in"
				+ "AND (r.timestamp_out < :timestamp_out OR r.timestamp_out IS NULL )"
				+ "AND r.time_start >= :time_start"
				+ "AND r.time_end < :time_end"
				+ "AND r.roadName = :roadname")
				.setParameter("timestamp_in", dateFormatTimestamp.format(date))
				.setParameter("timestamp_out", dateFormatTimestamp.format(date))
				.setParameter("time_start", dateFormatTime.format(date))
				.setParameter("time_end", dateFormatTime.format(date))
				.setParameter("roadname", roadName)
				.getSingleResult();
			return rate;
		} catch (NoResultException ex) {
			return 0.06;
		}
	}

	@Override
	public void AddDateOut(RoadRate rr, Date newdate) {
		rr.setTimestamp_out(newdate);
		em.merge(rr);

	}
}
