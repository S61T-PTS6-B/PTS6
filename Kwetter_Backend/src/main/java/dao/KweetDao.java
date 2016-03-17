/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Kweet;

/**
 *
 * @author koenv
 */
public interface KweetDao {
    /**
     *
     * Method to create a Kweet
     * 
     * @param k, the Kweet that needs to be created
     */
    public void create(Kweet k);
    
    /**
     *
     * Search for a Kweet, using the id of the Kweet
     * 
     * @param id, the Id of the Kweet
     * @return Kweet, a Kweet object
     */
    public Kweet find(Long id);
    
    /**
     * 
     * Method to find all Kweets
     *
     * @return List<Kweet>, a list full of Kweet objects
     */
    public List<Kweet> findAll();
}
