package com.ppvfra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registrarjurisdiction_crops")
public class RegistrarJurisdictionCrops implements Serializable{
	
	private Integer Id; 
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	@Column(name="roleid")
	private Integer roleid;
     
	@Column(name="userid")
	private Integer userid;
    
	 private Integer crops;
	
	
	private Boolean active;
	private Integer createdby;
	private Timestamp createdon;
	private Integer modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	
	public Integer getRoleid() {
		return roleid;
	}
 
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	

	public Integer getCrops() {
		return crops;
	}

	public void setCrops(Integer crops) {
		this.crops = crops;
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

	public Integer getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(Integer modifiedby) {
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
