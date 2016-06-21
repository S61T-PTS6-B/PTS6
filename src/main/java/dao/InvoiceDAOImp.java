/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.CarTracker;
import model.Invoice;
import model.NAW;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class InvoiceDAOImp implements InvoiceDAO {

	@PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
	EntityManager em;

	public InvoiceDAOImp() {
	}

	@Override
	public void createInvoice(Invoice i) {
		try {
			em.persist(i);
		} catch (Exception e) {
			System.out.println("Shit man");
		}
	}
	
	@Override
	public Invoice getInvoiceById(long id)
	{
		return em.find(Invoice.class, id);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		Query query;
		query = em.createQuery("SELECT i FROM INVOICE i");
		return (List<Invoice>) query.getResultList();
	}

	@Override
	public List<Invoice> getInvoiceByNAW(NAW naw) {
		ArrayList<CarTracker> cts = new ArrayList<>();
		try {
			List<CarTracker> ctWithID = em.createQuery("SELECT t.carid FROM CarOwner t WHERE t.nawid = :naw_id").setParameter("naw_id", naw).getResultList();
			for (CarTracker ct : ctWithID) {
				long id = ct.getId();
				cts.add((CarTracker) em.createQuery("SELECT x FROM CARTRACKER x WHERE x.id = " + id).getSingleResult());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ArrayList<Invoice> inWithCT = new ArrayList<>();
		for (CarTracker ct : cts) {
			inWithCT.add((Invoice) em.createQuery("SELECT i FROM INVOICE i WHERE i.car = :car").setParameter("car", ct).getSingleResult());
		}
		return inWithCT;

	}

	@Override
	public void payInvoice(Long id) {
		Invoice i = getInvoiceById(id);
		i.setPaid(true);
		em.merge(i);
	}

	@Override
	public List<Invoice> getPaidInvoicesByNAW(NAW naw) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void saveInvoice(Invoice in) {
		em.merge(in);
	}

	@Override
	public void sendLetter(Long invoiceID) {
		//STUUR EEN BRIEF NAAR DE BEWONER
		System.out.println("Stuur een brief. Naar factuur: " + invoiceID.toString());
	}

}
