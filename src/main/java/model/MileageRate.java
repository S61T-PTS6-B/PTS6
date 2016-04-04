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
@Entity (name = "MILEAGERATE")
public class MileageRate implements Serializable {
     
    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "tableGen",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "tableGen")
    private Long id;
    
    private double mileageRate;
    
    private String regio;
    private double prizecategory;
    private double tijdsinterval; //Casus, Wat wordt hier mee bedoelt?

    public MileageRate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMileageRate() {
        return mileageRate;
    }

    public void setMileageRate(double mileageRate) {
        this.mileageRate = mileageRate;
    }

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public double getPrizecategory() {
        return prizecategory;
    }

    public void setPrizecategory(double prizecategory) {
        this.prizecategory = prizecategory;
    }

    public double getTijdsinterval() {
        return tijdsinterval;
    }

    public void setTijdsinterval(double tijdsinterval) {
        this.tijdsinterval = tijdsinterval;
    }
    
    
    
}
