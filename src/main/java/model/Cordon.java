/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Max
 */
@Entity
public class Cordon {

	@Id
	private String placeName;
	private double amount;

	public Cordon() {
	}

	public Cordon(String placeName, double amount) {
		this.placeName = placeName;
		this.amount = amount;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Place : " + this.getPlaceName() + ", amount: " + this.amount;
	}
}
