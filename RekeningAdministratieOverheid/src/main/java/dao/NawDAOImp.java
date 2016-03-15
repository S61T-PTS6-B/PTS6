/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.NAW;

/**
 *
 * @author koenv
 */
@Stateless
public class NawDAOImp implements NawDAO{
    @PersistenceContext(unitName = "com.PTS6B_RekeningAdministratieOverheid_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public NawDAOImp() {
    }
   
    

    public void createNaw(NAW naw) {
        em.persist(naw);     
    }
    
}
