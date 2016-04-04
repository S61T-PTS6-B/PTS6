/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author koenv
 */
@Entity
public class CarOwner implements Serializable {    
      private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "tableGen",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "tableGen")
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
