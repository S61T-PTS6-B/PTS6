/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.KweetDao;
import dao.UserDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Kweet;
import model.KwetterUser;

/**
 *
 * @author koenv
 */
@Stateless
public class KwetterService implements IKwetterService{
    @EJB
    UserDao ud;
    
    @EJB
    KweetDao kd;

    public KwetterService() {

    }
    
    

    @Override
    public UserDao getUd() {
        return ud;
    }

    @Override
    public void setUd(UserDao ud) {
        this.ud = ud;
    }

    @Override
    public KweetDao getKd() {
        return kd;
    }

    @Override
    public void setKd(KweetDao kd) {
        this.kd = kd;
    }
    
    
    /**
     *
     * @param k
     */
    @Override
    public void createKweet(Kweet k)
    {
        kd.create(k);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Kweet> getKweets()
    {
        return kd.findAll();
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Kweet findKweet(long id)
    {
        return kd.find(id);
    }
    
        /**
     *
     * @param u
     */
    @Override
    public void createUser(KwetterUser u)
    {
        ud.create(u);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<KwetterUser> getUsers()
    {
        return ud.findAll();
    }
    
    /**
     *
     * @param username
     * @return
     */
    @Override
    public KwetterUser findUser(String username)
    {
        return ud.find(username);
    }
    
    /**
     *
     * @param owner
     * @param sheep
     */
    @Override
    public void addFollower(String owner, String sheep)
    {
        ud.addFollower(owner, sheep);
    }
    
    /**
     *
     * @param u
     * @return
     */
    @Override
    public List<KwetterUser> getFollowersByUser(KwetterUser u)
    {
        return ud.getAllFollowers(u);
    }
    
    /**
     *
     * @param username
     * @return
     */
    @Override
    public List<KwetterUser> getFollowingByUser(String username)
    {
        return ud.getAllFollowing(username);
    }
    
    
    
}