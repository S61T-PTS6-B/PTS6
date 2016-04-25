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
import model.CarOwner;
import model.CarTracker;
import model.NAW;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class CarOwnerDAOImp implements CarOwnerDAO{
    @PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
    EntityManager em;
    
    @Override
    public void createCarOwner(CarOwner co) {
        em.persist(co);
    }

    @Override
    public CarOwner getCarOwnerByNawId(NAW id) {
        Query query;
        query = em.createQuery("SELECT c FROM CAROWNER c WHERE nawid = :id").setParameter("id", id);
        return (CarOwner)query.getSingleResult(); //TODO
    }
    
}
