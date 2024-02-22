package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_rejuvenation")
public class Rejuvenation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long applicationid;
	private int candidatevariety;
	private String candidatevariety_other;
	private Date date_rejuvenation;
	private int type_line;
	private String denomination;
	private int quantity;
	private String place;
	private String findings; 
	private int no_of_packets_added_mts;
	private int total_packets_mts;
	private Long createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private Boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public long getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(long applicationid) {
		this.applicationid = applicationid;
	}
	public int getCandidatevariety() {
		return candidatevariety;
	}
	public void setCandidatevariety(int candidatevariety) {
		this.candidatevariety = candidatevariety;
	}
	public String getCandidatevariety_other() {
		return candidatevariety_other;
	}
	public void setCandidatevariety_other(String candidatevariety_other) {
		this.candidatevariety_other = candidatevariety_other;
	}
	public Date getDate_rejuvenation() {
		return date_rejuvenation;
	}
	public void setDate_rejuvenation(Date date_rejuvenation) {
		this.date_rejuvenation = date_rejuvenation;
	}
	public int getType_line() {
		return type_line;
	}
	public void setType_line(int type_line) {
		this.type_line = type_line;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFindings() {
		return findings;
	}
	public void setFindings(String findings) {
		this.findings = findings;
	}
	public int getNo_of_packets_added_mts() {
		return no_of_packets_added_mts;
	}
	public void setNo_of_packets_added_mts(int no_of_packets_added_mts) {
		this.no_of_packets_added_mts = no_of_packets_added_mts;
	}
	public int getTotal_packets_mts() {
		return total_packets_mts;
	}
	public void setTotal_packets_mts(int total_packets_mts) {
		this.total_packets_mts = total_packets_mts;
	}
	public Long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	public Long getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(Long modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}
	public String getCreatedbyip() {
		return createdbyip;
	}
	public void setCreatedbyip(String createdbyip) {
		this.createdbyip = createdbyip;
	}
	public String getModifiedbyip() {
		return modifiedbyip;
	}
	public void setModifiedbyip(String modifiedbyip) {
		this.modifiedbyip = modifiedbyip;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	

}
