/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import service.IRoadRateService;

/**
 *
 * @author koenv
 */
@Entity
public class Road implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    
    @EJB
    private IRoadRateService rrs;

    public Road(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return name;
    }
    
    public double getRate(Date date) {
        return rrs.getRoadRateByDate(name, date);
    }

    public void setId(String id) {
        this.name = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Road)) {
            return false;
        }
        Road other = (Road) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Road[ id=" + name + " ]";
    }

}
