/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.NawDAOImp;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy=GenerationType.TABLE,
            generator="tableGen")
    private Long id;
    
    private NAW naw;
    private double tariefCategorie;
    private String kenteken;
    private String modelAuto;
    private String merkAuto;
    private boolean rekeningrijdersWebsite;

    public CarTracker() {
    }
    
    public CarTracker(NAW naw, double tariefCategorie, String kenteken, String modelAuto, String merkAuto, boolean rekeningrijdersWebsite) {
        this.naw = naw;
        this.tariefCategorie = tariefCategorie;
        this.kenteken = kenteken;
        this.modelAuto = modelAuto;
        this.merkAuto = merkAuto;
        this.rekeningrijdersWebsite = rekeningrijdersWebsite;
    }

    public NAW getNaw() {
        return naw;
    }

    public void setNaw(NAW naw) {
        this.naw = naw;
    }

    public double getTariefCategorie() {
        return tariefCategorie;
    }

    public void setTariefCategorie(double tariefCategorie) {
        this.tariefCategorie = tariefCategorie;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public String getModelAuto() {
        return modelAuto;
    }

    public void setModelAuto(String modelAuto) {
        this.modelAuto = modelAuto;
    }

    public String getMerkAuto() {
        return merkAuto;
    }

    public void setMerkAuto(String merkAuto) {
        this.merkAuto = merkAuto;
    }

    public boolean isRekeningrijdersWebsite() {
        return rekeningrijdersWebsite;
    }

    public void setRekeningrijdersWebsite(boolean rekeningrijdersWebsite) {
        this.rekeningrijdersWebsite = rekeningrijdersWebsite;
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
