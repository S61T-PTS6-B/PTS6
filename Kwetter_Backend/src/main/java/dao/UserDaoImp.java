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
import model.KwetterUser;

/**
 *
 * @author koenv
 */
@Local
@Stateless
public class UserDaoImp implements UserDao{
    
    @PersistenceContext(unitName = "com.JEA_Kwetter_Backend_war_1.0-SNAPSHOTPU")
    EntityManager em;

    /**
     *
     */
    public UserDaoImp() {
        
    }
    
    /**
     *
     * @param u
     */
    @Override
    public void create(KwetterUser u) {
        em.persist(u);     
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<KwetterUser> findAll()
    { 
       Query query = em.createQuery("SELECT * FROM KwetterUser");
       return (List<KwetterUser>)query.getResultList(); //TODO
    }

    /**
     *
     * @param username
     * @param id
     * @return
     */
    @Override
    public KwetterUser find(String username) {
//        if (!users.containsKey(id)) {
//            throw new IllegalArgumentException("Id no found" + id);
//        }
//        return users.get(id);
        Query query = em.createQuery("SELECT * FROM KwetterUser WHERE username = :uname")
                .setParameter("uname", username);
        return (KwetterUser)query.getSingleResult(); //TODO
        
    }


    @Override
    public void addFollower(String usernameFollowing, String usernameFollowedBy) {
       
        KwetterUser user1 = em.find(KwetterUser.class, usernameFollowing);
        KwetterUser user2 = em.find(KwetterUser.class, usernameFollowedBy);
        user1.addFollowing(user2);
        user2.addFollowedBy(user1);
        em.merge(user1);
        em.merge(user2);
        
    }


    /**
     *
     * @param u
     * @return
     */
    @Override
    public List<KwetterUser> getAllFollowers(KwetterUser u) {
        return u.getFollowedBy();
    }

    /**
     *
     * @param u
     * @return
     */
    @Override
    public List<KwetterUser> getAllFollowing(String username) {
        KwetterUser ku1 = em.find(KwetterUser.class, username);
        return ku1.getFollowing();
    }
}
