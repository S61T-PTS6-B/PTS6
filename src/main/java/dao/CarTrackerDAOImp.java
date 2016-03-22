/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class CarTrackerDAOImp implements CarTrackerDAO{

    @PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public CarTrackerDAOImp() {
    }
    
    
    @Override
    public void createCarTracker(CarTracker ct) {
        em.persist(ct);     
    }
    
    @Override
    public List<CarTracker> getAllCarTrackers() {
        Query query;
        query = em.createQuery("SELECT c FROM CARTRACKER c");
        return (List<CarTracker>)query.getResultList(); //TODO
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public CarTracker getCarTrackerByNaw(NAW naw) {
        CarTracker ct = (CarTracker) em.createQuery("SELECT t FROM CARTRACKER t WHERE t.naw = :naw_id").setParameter("naw_id", naw).getSingleResult();
        return ct;
    }

    @Override
    public void changeOwner(CarTracker ct, NAW naw) {
        //ct.setNaw(naw);
        //TODO
    }

    @Override
    public void changePrizeCategory(CarTracker ct, double prizecategory) {
        ct.setTariefCategorie(prizecategory);
        em.merge(ct);
        
    }

    @Override
    public void changeLicensePlate(CarTracker ct, String licenseplate) {
        ct.setKenteken(licenseplate);
    }

    @Override
    public void changeModelCar(CarTracker ct, String modelcar) {
        ct.setModelAuto(modelcar);
    }

    @Override
    public void changeBrandCar(CarTracker ct, String brandcar) {
        ct.setMerkAuto(brandcar);
        em.merge(ct);
    }

    @Override
    public void changeWebsiteSubscription(CarTracker ct, Boolean subscription) {
        ct.setRekeningrijdersWebsite(subscription);
    }
    
    

}
