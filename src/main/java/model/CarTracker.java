/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.NawDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    private double prizeCategory;
    private String licensePlate;
    private String modelCar;
    private String brandCar;
    private boolean websiteSubscription;

    public CarTracker() {
    }

    public CarTracker(double prizeCategory, String licensePlate, String modelCar, String brandCar, boolean websiteSubscription) {
        this.prizeCategory = prizeCategory;
        this.licensePlate = licensePlate;
        this.modelCar = modelCar;
        this.brandCar = brandCar;
        this.websiteSubscription = websiteSubscription;
    }

    public double getTariefCategorie() {
        return prizeCategory;
    }

    public void setTariefCategorie(double tariefCategorie) {
        this.prizeCategory = tariefCategorie;
    }

    public String getKenteken() {
        return licensePlate;
    }

    public void setKenteken(String kenteken) {
        this.licensePlate = kenteken;
    }

    public String getModelAuto() {
        return modelCar;
    }

    public void setModelAuto(String modelAuto) {
        this.modelCar = modelAuto;
    }

    public String getMerkAuto() {
        return brandCar;
    }

    public void setMerkAuto(String merkAuto) {
        this.brandCar = merkAuto;
    }

    public boolean isRekeningrijdersWebsite() {
        return websiteSubscription;
    }

    public void setRekeningrijdersWebsite(boolean rekeningrijdersWebsite) {
        this.websiteSubscription = rekeningrijdersWebsite;
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

    @Override
    public String toString() {
        return "model.CarTracker[ id=" + id + " ]";
    }

}