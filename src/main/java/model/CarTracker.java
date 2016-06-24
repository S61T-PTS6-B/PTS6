/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author koenv
 */
@Entity(name = "CARTRACKER")
public class CarTracker implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "tableGen",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "tableGen")
    private Long id;
    
    @OneToMany
    private List<CarOwner> carowners;
    private String priceCategory;
    private String licensePlate;
    private String modelCar;
    private String brandCar;

    public CarTracker() {
    } 
    

    public CarTracker(String prizeCategory, String licensePlate, String modelCar, String brandCar) {
        this.priceCategory = prizeCategory;
        this.licensePlate = licensePlate;
        this.modelCar = modelCar;
        this.brandCar = brandCar;
    }

    public String getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(String tariefCategorie) {
        this.priceCategory = tariefCategorie;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String kenteken) {
        this.licensePlate = kenteken;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelAuto) {
        this.modelCar = modelAuto;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String merkAuto) {
        this.brandCar = merkAuto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarTracker)) {
            return false;
        }
        CarTracker other = (CarTracker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



}
