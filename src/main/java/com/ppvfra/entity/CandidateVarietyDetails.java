package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_candidate_variety_details")
public class CandidateVarietyDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private Long applicationid;
	private int candidate_variety_id;
	private String candidate_variety_other;
	private int createdby;
	private Timestamp createdon;
	private String createdbyip;
	private String modifiedbyip;
	private int modifiedby;
	private Timestamp modifiedon;
	
	public Long getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
	}
	public int getCandidate_variety_id() {
		return candidate_variety_id;
	}
	public void setCandidate_variety_id(int candidate_variety_id) {
		this.candidate_variety_id = candidate_variety_id;
	}
	public String getCandidate_variety_other() {
		return candidate_variety_other;
	}
	public void setCandidate_variety_other(String candidate_variety_other) {
		this.candidate_variety_other = candidate_variety_other;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
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
	public int getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}

}
