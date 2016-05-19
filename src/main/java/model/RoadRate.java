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
import javax.persistence.Temporal;

/**
 *
 * @author koenv
 */
@Entity
public class RoadRate implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String roadname;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date timestamp_in;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date timestamp_out;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date time_start;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date time_end;

	private double rate;

	public RoadRate() {
	}

	public RoadRate(Long id, String roadname, Date timestamp_in, Date timestamp_out, Date time_start, Date time_end, double rate) {
		this.id = id;
		this.roadname = roadname;
		this.timestamp_in = timestamp_in;
		this.timestamp_out = timestamp_out;
		this.time_start = time_start;
		this.time_end = time_end;
		this.rate = rate;
	}

	public String getRoadname() {
		return roadname;
	}

	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}

	public Date getTimestamp_in() {
		return timestamp_in;
	}

	public void setTimestamp_in(Date timestamp_in) {
		this.timestamp_in = timestamp_in;
	}

	public Date getTimestamp_out() {
		return timestamp_out;
	}

	public void setTimestamp_out(Date timestamp_out) {
		this.timestamp_out = timestamp_out;
	}

	public Date getTime_start() {
		return time_start;
	}

	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}

	public Date getTime_end() {
		return time_end;
	}

	public void setTime_end(Date time_end) {
		this.time_end = time_end;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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
		if (!(object instanceof RoadRate)) {
			return false;
		}
		RoadRate other = (RoadRate) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.RoadRage[ id=" + id + " ]";
	}

}
