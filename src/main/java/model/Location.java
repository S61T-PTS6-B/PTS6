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
import javax.persistence.TableGenerator;

/**
 *
 * @author Max
 */
@Entity
public class Location implements Serializable, Comparable<Location> {

	@TableGenerator(
		name = "tableGen",
		allocationSize = 1,
		initialValue = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,
		generator = "tableGen")
	private double longitude;
	private double latitude;
	private String road;
	private Date date;
	private String city;

	public Location() {
	}
	
	

	public Location(double latitude, double longitude) {
		this.date = new Date();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "(" + this.latitude + ", " + this.longitude + ") on road: " + this.road;
	}

	@Override
	public int compareTo(Location o) {
		return getDate().compareTo(o.getDate());
	}
}