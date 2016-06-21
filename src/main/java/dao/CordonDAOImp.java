/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.CarTracker;
import model.Cordon;

/**
 *
 * @author koenv
 */
public class CordonDAOImp implements CordonDAO {

	@PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
	EntityManager em;

	public CordonDAOImp() {
	}

	public void createCordon(Cordon c) {
		em.persist(c);
	}

	@Override
	public List<Cordon> getAllCordons() {
		Query query;
		query = em.createQuery("SELECT c FROM CORDON c");
		return (List<Cordon>) query.getResultList(); //TODO
	}

	@Override
	public Cordon getCordonByName(String name) {
		Query query;
		query = em.createQuery("SELECT c FROM CORDON c WHERE c.placeName = " + name);
		return (Cordon) query.getSingleResult(); //TODO
	}

	@Override
	public void changeRate(Double rate, Cordon c) {
		c.setAmount(rate);
		em.merge(c);
	}

}
