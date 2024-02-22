package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_pre_grant_opposition")
public class ApplicationPreGrantOpposition 
{
	
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    public Integer getId() {
		return id;
	}
     

	public void setId(int id) {
		this.id = id;
	}
    
	 private Long applicationid ;
	 private String is_pre_grant_opposition_received ;
	 private String opposition_status ;
	 private String opposition_filed_by;
	 private Date  date_of_deceision ;
	 private String remarks ;
	 private Long createdby ;
	 private Date createdon ;
	 private String   createdbyip ;
	 private Long  modifiedby ;
	 private Date  modifiedon;
	 private String modifiedbyip ;
	 private Boolean active ;
	 private String record_language ;

	public Long getApplicationid() {
		return applicationid;
	}


	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
	}


	public String getIs_pre_grant_opposition_received() {
		return is_pre_grant_opposition_received;
	}


	public void setIs_pre_grant_opposition_received(String is_pre_grant_opposition_received) {
		this.is_pre_grant_opposition_received = is_pre_grant_opposition_received;
	}

	public String getOpposition_filed_by() {
		return opposition_filed_by;
	}


	public String getOpposition_status() {
		return opposition_status;
	}


	public void setOpposition_status(String opposition_status) {
		this.opposition_status = opposition_status;
	}


	public void setOpposition_filed_by(String opposition_filed_by) {
		this.opposition_filed_by = opposition_filed_by;
	}


	public Date getDate_of_deceision() {
		return date_of_deceision;
	}


	public void setDate_of_deceision(Date date_of_deceision) {
		this.date_of_deceision = date_of_deceision;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Long getCreatedby() {
		return createdby;
	}


	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}


	public Date getCreatedon() {
		return createdon;
	}


	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}


	public String getCreatedbyip() {
		return createdbyip;
	}


	public void setCreatedbyip(String createdbyip) {
		this.createdbyip = createdbyip;
	}


	public Long getModifiedby() {
		return modifiedby;
	}


	public void setModifiedby(Long modifiedby) {
		this.modifiedby = modifiedby;
	}


	public Date getModifiedon() {
		return modifiedon;
	}


	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
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


	public String getRecord_language() {
		return record_language;
	}


	public void setRecord_language(String record_language) {
		this.record_language = record_language;
	}
	
	

	
}