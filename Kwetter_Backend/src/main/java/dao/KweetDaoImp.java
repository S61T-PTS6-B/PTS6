/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Kweet;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class KweetDaoImp implements KweetDao {
    
    @PersistenceContext(unitName = "com.JEA_Kwetter_Backend_war_1.0-SNAPSHOTPU")
    EntityManager em;

    private Long kweetId;
    private final HashMap<Long, Kweet> kweets;

    public KweetDaoImp() {
        kweets = new HashMap<>();
    }

    /**
     *
     * @param k
     */
    @Override
    public void create(Kweet k) {
        if (k == null) {
            throw new IllegalArgumentException("Kweet is null");
        }
        em.persist(k);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Kweet> findAll()
    {
        return new ArrayList(kweets.values());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Kweet find(Long id) {
        if (!kweets.containsKey(id)) {
            throw new IllegalArgumentException("Id no found" + id);
        }
        return kweets.get(id);
    }

}
