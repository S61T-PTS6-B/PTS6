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
import model.MileageRate;
import model.NAW;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class MileageRateDAOImp implements MileageRateDAO{
    @PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
    EntityManager em;
    
    @Override
    public void createMileageRate(MileageRate mr) {
        em.persist(mr);
    }

    @Override
    public List<MileageRate> getAllRates() {
        Query query;
        query = em.createQuery("SELECT m FROM MILEAGERATE m");
        return (List<MileageRate>)query.getResultList(); //TODO
    }
    
    @Override    
    public MileageRate getRatePerId(String id) {
        long realid = Long.parseLong(id);
        Query query;
        query = em.createQuery("SELECT m FROM MILEAGERATE m WHERE m.id = :id").setParameter("id", realid);
        return (MileageRate) query.getSingleResult();
        
    }

    @Override
    public List<MileageRate> getRatePerArea(String regio) {
        Query query;
        query = em.createQuery("SELECT m FROM MILEAGERATE m WHERE m.regio = :regio").setParameter("regio", regio);
        return (List<MileageRate>)query.getResultList(); //TODO
    }

    @Override
    public List<MileageRate> getRatePerPrizeCategory(double prizecategory) {
        Query query;
        query = em.createQuery("SELECT m FROM MILEAGERATE m WHERE m.prizecategory = :pc").setParameter("pc", prizecategory);
        return (List<MileageRate>)query.getResultList(); //TODO
    }

    @Override
    public List<MileageRate> getRatePerInterval(double interval) {
        Query query;
        query = em.createQuery("SELECT m FROM MILEAGERATE m WHERE m.tijdsinterval = :ti").setParameter("ti", interval);
        return (List<MileageRate>)query.getResultList(); //TODO
    }

    @Override
    public List<MileageRate> getRatePerRate(double mileageRate) {
        Query query;
        query = em.createQuery("SELECT m FROM MILEAGERATE m WHERE m.mileageRate = :mrr").setParameter("mrr", mileageRate);
        return (List<MileageRate>)query.getResultList(); //TODO
    }

    @Override
    public void changeRate(MileageRate mr, double mileageRate) {
        mr.setMileageRate(mileageRate);
        em.merge(mr);
    }

    @Override
    public void changeRegio(MileageRate mr, String regio) {
        mr.setRegio(regio);
        em.merge(mr);
    }

    @Override
    public void changePrizeCategory(MileageRate mr, double prizeCategory) {
        mr.setPricecategory(prizeCategory);
        em.merge(mr);
    }

    @Override
    public void changeInterval(MileageRate mr, double interval) {
        mr.setTijdsinterval(interval);
        em.merge(mr);
    }
    
}
