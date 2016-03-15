/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.CarTracker;

/**
 *
 * @author koenv
 */
@Stateless
public class CarTrackerDAOImp implements CarTrackerDAO{

    @PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public CarTrackerDAOImp() {
    }
    
    
    public void createCarTracker(CarTracker ct) {
        em.persist(ct);     
    }
    
    public List<CarTracker> getAllCarTrackers() {
        Query query;
        query = em.createQuery("SELECT * FROM cartracker");
        return (List<CarTracker>)query.getResultList(); //TODO
    }

}
