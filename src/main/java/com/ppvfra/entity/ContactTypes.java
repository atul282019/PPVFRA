package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_contacttypes")
public class ContactTypes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    private String type;
    private String description;
    private int createdby;
    private Timestamp createdon;
    private int modifiedby;
    private Timestamp modifiedon;
    private Boolean active;
    private String createdbyip;
    private String modifiedbyip;
    private String description_hin;
    private String type_hin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
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
	public String getDescription_hin() {
		return description_hin;
	}
	public void setDescription_hin(String description_hin) {
		this.description_hin = description_hin;
	}
	public String getType_hin() {
		return type_hin;
	}
	public void setType_hin(String type_hin) {
		this.type_hin = type_hin;
	}
	

}
