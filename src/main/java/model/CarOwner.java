/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author koenv
 */
@Entity
public class CarOwner implements Serializable {    
    @Id @GeneratedValue
    private Long id;
    
    private CarTracker carid;
   
    private NAW nawid;  
    
    private boolean active;

    public CarOwner() {
    }
    
    

    public CarOwner(CarTracker ct, NAW n) {
        this.carid = ct;
        this.nawid = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
