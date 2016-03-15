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
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

/**
 *
 * @author koenv
 */
@Entity
public class NAW implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
    name = "tableGen",
    allocationSize = 1,
    initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,
            generator="tableGen")
    private Long id;
    
    private String firstname;
    private String lastname;
    private String address;
    private String number;
    private String zipcode;
    private String city;
    private String telephone;

    public NAW() {
    }

    
    public NAW(String firstname, String lastname, String address, String number, String zipcode, String city, String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.telephone = telephone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
        if (!(object instanceof NAW)) {
            return false;
        }
        NAW other = (NAW) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.NAW[ id=" + id + " ]";
    }
    
}
