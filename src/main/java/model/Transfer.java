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
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author koenv
 */
@Entity
public class Transfer implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@OneToOne
	private CarTracker id;
	
	private String sellcode;
	
	@OneToOne
	private NAW naw;

	public Transfer() {
	}

	public CarTracker getId() {
		return id;
	}

	public void setId(CarTracker id) {
		this.id = id;
	}

	public String getSellcode() {
		return sellcode;
	}

	public void setSellcode(String sellcode) {
		this.sellcode = sellcode;
	}

	public NAW getNaw() {
		return naw;
	}

	public void setNaw(NAW naw) {
		this.naw = naw;
	}

	
	
	
}
