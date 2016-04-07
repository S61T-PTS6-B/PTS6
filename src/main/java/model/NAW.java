/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

/**
 *
 * @author koenv
 */
@Entity(name="NAW")
public class NAW implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int bsn;
    
    private String firstname;
    private String lastname;
    private String address;
    private String number;
    private String zipcode;
    private String city;
    private String telephone;
    
    @Column(unique = true)
    private String email;
    
    
    @OneToMany
    private List<CarOwner> carowners;

    public NAW() {
    }

    
    public NAW(int bsn, String firstname, String lastname, String address, String number, String zipcode, String city, String telephone, String email) {
        this.bsn = bsn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.telephone = telephone;
        this.email = email;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
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
    
//    public void addCarTracker(CarTracker ct) {
//        cartrackers.add(ct);
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public String toString() {
        return "model.NAW[ bsn=" + bsn + " ]";
    }
    
}
