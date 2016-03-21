/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.CarTracker;
import model.NAW;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.CarTrackerService;
import service.NAWService;

/**
 *
 * @author koenv
 */
public class testDB {
    
    CarTrackerService cts;
    
    NAWService ns;
    
    
    public testDB() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddCartracker() {
        ns = new NAWService();
        NAW n = new NAW("Koen", "van der Borght", "Professor Rogierhof", "54", "5014NT", "Tilburg", "0621876767");
        ns.createNAW(n);
        assertEquals(ns.getNAWById("1").getFirstname(), "Koen");
        
    }

    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
