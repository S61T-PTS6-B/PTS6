/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.KwetterUser;

/**
 *
 * @author koenv
 */
public interface UserDao {
     
    /**
     *
     * @param u
     */
    public void create(KwetterUser u);
    
    /**
     *
     * @param username
     * @return
     */
    public KwetterUser find(String username);
    
    /**
     *
     * @return
     */
    public List<KwetterUser> findAll();
    
    /**
     *
     * @param usernameFollowing
     * @param usernameFollowedBy
     */
    public void addFollower(String usernameFollowing, String usernameFollowedBy);

    /**
     *
     * @param u
     * @return
     */
    public List<KwetterUser> getAllFollowers(KwetterUser u);
    
    /**
     *
     * @param username
     * @return
     */
    public List<KwetterUser> getAllFollowing(String username);
}
