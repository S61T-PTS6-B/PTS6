/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import calculations.SeriesOfLocationsOnRoad;
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
@Entity
public class Invoice {

	public Invoice() {
		this.seriesOfLocationsOnRoad = new ArrayList<>();
		this.cordonOccurrences = new ArrayList<>();
	}
	
	
	@TableGenerator(
		name = "tableGen",
		allocationSize = 1,
		initialValue = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,
		generator = "tableGen")
	private Long id;
	private double totalAmount;
	private int month;
	private int year;
	private boolean paid;
	List<SeriesOfLocationsOnRoad> seriesOfLocationsOnRoad;
	private double totalDistance;
	private List<Cordon> cordonOccurrences;

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void addToTotalAmount(double kilometers, double rate) {
		this.totalAmount += kilometers * rate;
		System.out.println("The rate was " + rate + "| " + kilometers + " kilometers * " + rate + " euro's = " + (kilometers * rate) + " euro's, which brings the total to " + this.totalAmount);
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
	
	
}
