/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.KweetDao;
import dao.UserDao;
import java.util.List;
import model.Kweet;
import model.KwetterUser;

/**
 *
 * @author koenv
 */
public interface IKwetterService {
    public UserDao getUd();
    
    public void setUd(UserDao ud);
    
    public KweetDao getKd();
    
    public void setKd(KweetDao kd);
    
    public void createKweet(Kweet k);
    
    public List<Kweet> getKweets();
    
    public Kweet findKweet(long id);
    
    public void createUser(KwetterUser u);
    
    public List<KwetterUser> getUsers();
    
    public KwetterUser findUser(String username);
    
    public void addFollower(String owner, String sheep);
    
    public List<KwetterUser> getFollowersByUser(KwetterUser u);
    
    public List<KwetterUser> getFollowingByUser(String username);
}
