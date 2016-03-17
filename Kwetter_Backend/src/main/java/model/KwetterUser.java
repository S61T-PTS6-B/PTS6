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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author koenv
 */
@Entity
public class KwetterUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(unique = true)
    private String username;
    private String realname;
    private String location; 
    private String web;
    private String bio;
    
    @ManyToMany
    private List<KwetterUser> followedBy;
    
    @ManyToMany(mappedBy="followedBy")
    private List<KwetterUser> following;
    
    @OneToMany(mappedBy="author")
    private List<Kweet> kweets;

    public KwetterUser()
    {
        
    }
    
    public KwetterUser(String username, String realname, String location, String web, String bio) {
        this.username = username;
        this.realname = realname;
        this.location = location;
        this.web = web;
        this.bio = bio;
    }
    
    public KwetterUser(String name)
    {
        this.username = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }   

    public List<KwetterUser> getFollowedBy() {
        return followedBy;
    }

    public void addFollowedBy(KwetterUser u)
    {
        this.followedBy.add(u);
    }

    public List<KwetterUser> getFollowing() {
        return following;
    }

    public void addFollowing(KwetterUser u)
    {
        this.following.add(u);
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
        if (!(object instanceof KwetterUser)) {
            return false;
        }
        KwetterUser other = (KwetterUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.KwetterUser[ id=" + id + " ]";
    }
    
}
