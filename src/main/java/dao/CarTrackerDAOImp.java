/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
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
    public ArrayList<CarTracker> getCarTrackerByNaw(NAW naw) {
        ArrayList<CarTracker> result = new ArrayList<>();
        try {
            
            List<CarTracker> ctWithID = em.createQuery("SELECT t.carid FROM CarOwner t WHERE t.nawid = :naw_id").setParameter("naw_id", naw).getResultList();
            for(CarTracker ct : ctWithID)
            {
                long id = ct.getId(); 
                result.add((CarTracker) em.createQuery("SELECT x FROM CARTRACKER x WHERE x.id = " + id).getSingleResult());
            }        
        }
        catch (Exception e) {
           System.out.println(e.getMessage());
        }
        

        return result;
    }

    @Override
    public void changeOwner(CarTracker ct, NAW naw) {
        //ct.setNaw(naw);
        //TODO
    }

    @Override
    public void changePrizeCategory(CarTracker ct, double prizecategory) {
        ct.setPriceCategory(prizecategory);
        em.merge(ct);
        
    }

    @Override
    public void changeLicensePlate(CarTracker ct, String licenseplate) {
        ct.setLicensePlate(licenseplate);
        em.merge(ct);
    }

    @Override
    public void changeModelCar(CarTracker ct, String modelcar) {
        ct.setModelCar(modelcar);
        em.merge(ct);
    }

    @Override
    public void changeBrandCar(CarTracker ct, String brandcar) {
        ct.setBrandCar(brandcar);
        em.merge(ct);
    }

    @Override
    public void changeWebsiteSubscription(CarTracker ct, Boolean subscription) {
        ct.setRekeningrijdersWebsite(subscription);
    }

    @Override
    public CarTracker getSingleCarTrackerByNaw(NAW naw) {
        CarTracker ctWithId = (CarTracker)em.createQuery("SELECT t.carid FROM CarOwner t WHERE t.nawid = :naw_id").setParameter("naw_id", naw).getSingleResult();
        long id = ctWithId.getId();
        return (CarTracker)em.createQuery("SELECT x FROM CARTRACKER x WHERE x.id = " + id).getSingleResult();
    }

    @Override
    public CarTracker getSingleCarTrackerById(long id) {
        return (CarTracker)em.createQuery("SELECT x FROM CARTRACKER x WHERE x.id = " + id).getSingleResult();
    }
    
    

}
