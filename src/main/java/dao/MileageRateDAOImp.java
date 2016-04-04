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
import model.MileageRate;
import model.NAW;

/**
 *
 * @author koenv
 */
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
    
}
