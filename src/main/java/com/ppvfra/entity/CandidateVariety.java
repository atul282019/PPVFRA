package com.ppvfra.entity;

 
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="m_candidatevariety")
public class CandidateVariety
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	
	 
	  
	    private String candidate_variety ;
	    public void setCreatedby(Integer createdby) {
			this.createdby = createdby;
		}
		private String description ;
	   private Integer createdby;
	    private Timestamp createdon;
	   private Integer  modifiedby;
	   public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCandidate_variety() {
		return candidate_variety;
	}
	public void setCandidate_variety(String candidate_variety) {
		this.candidate_variety = candidate_variety;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public String getRecord_language() {
		return record_language;
	}
	public void setRecord_language(String record_language) {
		this.record_language = record_language;
	}
	public String getCandidate_variety_hin() {
		return candidate_variety_hin;
	}
	public void setCandidate_variety_hin(String candidate_variety_hin) {
		this.candidate_variety_hin = candidate_variety_hin;
	}
	public String getDescription_hin() {
		return description_hin;
	}
	public void setDescription_hin(String description_hin) {
		this.description_hin = description_hin;
	}
	private Timestamp modifiedon;
	    private boolean active;
	    private String createdbyip;
	    private String modifiedbyip ;
	    private String record_language ;
	    private String candidate_variety_hin ;
	    private String description_hin ;
	   
	   
}