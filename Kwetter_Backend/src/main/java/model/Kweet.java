/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author koenv
 */
@Entity
public class Kweet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeplaced;
    
    @ManyToOne
    private KwetterUser author;
    //private List<KwetterUser> mentions;

    
    public Kweet() {
        
    }
    

    public Kweet(String content, Date timeplaced, KwetterUser author) {
        this.content = content;
        this.timeplaced = timeplaced;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeplaced() {
        return timeplaced;
    }

    public void setTimeplaced(Date timeplaced) {
        this.timeplaced = timeplaced;
    }

    public KwetterUser getAuthor() {
        return author;
    }

    public void setAuthor(KwetterUser author) {
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
//    public void addMention(KwetterUser u)
//    {
//        mentions.add(u);
//    }
//
//    public List<KwetterUser> getMentions() {
//        return mentions;
//    }
//
//    public void setMentions(List<KwetterUser> mentions) {
//        this.mentions = mentions;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kweet)) {
            return false;
        }
        Kweet other = (Kweet) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Kweet[ id=" + id + " ]";
    }
    
}
