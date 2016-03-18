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
public class NawDAOImp implements NawDAO{
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
        return (List<NAW>)query.getResultList(); //TODO
    }

    @Override
    public NAW getNawById(long id) {
        NAW naw = em.find(NAW.class, id);
        return naw;
    }
    
}
