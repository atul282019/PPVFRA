package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="application_sts_test")
public class ApplicationStsTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int applicationid;
	/* private int seedreceived_id; */
	private String testtype;
	private int year;
	private String season;
	private String cell_location;
	private Integer lead_center;
	private Integer coopcenter;
	private String mode_of_delivery;
	private String seed_sent_to_location;
	private String speedpost_no;
	private String tracking;
	private Date dateof_seadsent;
	private int no_of_final_packets;
	private Integer createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(int applicationid) {
		this.applicationid = applicationid;
	}

	/*
	 * public int getSeedreceived_id() { return seedreceived_id; } public void
	 * setSeedreceived_id(int seedreceived_id) { this.seedreceived_id =
	 * seedreceived_id; }
	 */
	public String getTesttype() {
		return testtype;
	}
	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getCell_location() {
		return cell_location;
	}
	public void setCell_location(String cell_location) {
		this.cell_location = cell_location;
	}
	 
	public Integer getLead_center() {
		return lead_center;
	}
	public void setLead_center(Integer lead_center) {
		this.lead_center = lead_center;
	}
	public Integer getCoopcenter() {
		return coopcenter;
	}
	public void setCoopcenter(Integer coopcenter) {
		this.coopcenter = coopcenter;
	}
	public String getMode_of_delivery() {
		return mode_of_delivery;
	}
	public void setMode_of_delivery(String mode_of_delivery) {
		this.mode_of_delivery = mode_of_delivery;
	}
	public String getSeed_sent_to_location() {
		return seed_sent_to_location;
	}
	public void setSeed_sent_to_location(String seed_sent_to_location) {
		this.seed_sent_to_location = seed_sent_to_location;
	}
	public String getSpeedpost_no() {
		return speedpost_no;
	}
	public void setSpeedpost_no(String speedpost_no) {
		this.speedpost_no = speedpost_no;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public Date getDateof_seadsent() {
		return dateof_seadsent;
	}
	public void setDateof_seadsent(Date dateof_seadsent) {
		this.dateof_seadsent = dateof_seadsent;
	}
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
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
	public int getNo_of_final_packets() {
		return no_of_final_packets;
	}
	public void setNo_of_final_packets(int no_of_final_packets) {
		this.no_of_final_packets = no_of_final_packets;
	}		
}