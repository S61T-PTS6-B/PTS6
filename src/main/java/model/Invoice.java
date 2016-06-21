/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import calculations.SeriesOfLocationsOnRoad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author Max
 */
@Entity(name = "INVOICE")
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	public Invoice() {

	}
//
//	public Invoice() {
//		this.seriesOfLocationsOnRoad = new ArrayList<>();
//		this.cordonOccurrences = new ArrayList<>();
//	}

	@TableGenerator(
		name = "tableGen",
		allocationSize = 1,
		initialValue = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,
		generator = "tableGen")
	private Long id;
	private CarTracker car;

	private double totalAmount;
	private int month;
	private int year;
	private boolean paid;
	private String URLToDownload;
	transient List<SeriesOfLocationsOnRoad> seriesOfLocationsOnRoad;
	private double totalDistance;
	private transient List<Cordon> cordonOccurrences;

	
	public Invoice(CarTracker car) {
		this.car = car;
		this.seriesOfLocationsOnRoad = new ArrayList<>();
		this.cordonOccurrences = new ArrayList<>();
	}

	public CarTracker getCar() {
		return car;
	}

	public void setCar(CarTracker car) {
		this.car = car;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void addToTotalAmount(double kilometers, double rate) {
		this.totalAmount += kilometers * rate;
	}

	public List<SeriesOfLocationsOnRoad> getSeriesOfLocationsOnRoad() {
		return seriesOfLocationsOnRoad;
	}

	public void setSeriesOfLocationsOnRoad(List<SeriesOfLocationsOnRoad> seriesOfLocationsOnRoad) {
		this.seriesOfLocationsOnRoad = seriesOfLocationsOnRoad;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public void addToTotalDistance(double distance) {
		this.totalDistance += distance;
	}

	public List<Cordon> getCordonOccurrences() {
		return cordonOccurrences;
	}

	public void addCordonOccurrence(Cordon cordon) {
		this.totalAmount += cordon.getAmount();
		this.cordonOccurrences.add(cordon);
	}

	public String cordonOccurrencesString() {
		String result = "Cordons: \n\n";
		for (Cordon c : this.cordonOccurrences) {
			result += c.getPlaceName() + " - " + c.getAmount() + "euro\n";
		}
		return result;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getURLToDownload() {
		return URLToDownload;
	}

	public void setURLToDownload(String URLToDownload) {
		this.URLToDownload = URLToDownload;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
