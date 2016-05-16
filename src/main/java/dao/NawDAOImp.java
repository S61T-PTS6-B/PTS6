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
import javax.persistence.Query;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class NawDAOImp implements NawDAO {

	@PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
	EntityManager em;

	public NawDAOImp() {
	}

	@Override
	public void createNaw(NAW naw) {
		em.persist(naw);
	}

	@Override
	public List<NAW> getAllNaws() {
		Query query;
		query = em.createQuery("SELECT n FROM NAW n");
		return (List<NAW>) query.getResultList(); //TODO
	}

	@Override
	public NAW getNawByBsn(int bsn) {
		NAW naw = em.find(NAW.class, bsn);
		return naw;
	}

	@Override
	public NAW changeMail(NAW naw, String newmail) {
		naw.setEmail(newmail);
		em.merge(naw);
		return naw;
	}
	
	@Override
	public NAW changePhone(NAW naw, String newphone) {
		naw.setTelephone(newphone);
		em.merge(naw);
		return naw;
	}
	
	@Override
	public NAW changeFirstname(NAW naw, String newfirstname) {
		naw.setFirstname(newfirstname);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeLastname(NAW naw, String newlastname) {
		naw.setLastname(newlastname);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeAddress(NAW naw, String newstreet) {
		naw.setAddress(newstreet);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeNumber(NAW naw, String newnumber) {
		naw.setNumber(newnumber);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeZipcode(NAW naw, String newzipcode) {
		naw.setZipcode(newzipcode);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeCity(NAW naw, String newcity) {
		naw.setCity(newcity);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeTelephone(NAW naw, String newtelephone) {
		naw.setTelephone(newtelephone);
		em.merge(naw);
		return naw;
	}

	@Override
	public NAW changeEmail(NAW naw, String newmail) {
		naw.setEmail(newmail);
		em.merge(naw);
		return naw;
	}
}
