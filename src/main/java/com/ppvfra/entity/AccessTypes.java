package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_accesstypes")
public class AccessTypes 
{
	
	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    public Integer getId() {
		return id;
	}
     

	public void setId(int id) {
		this.id = id;
	}
    
    private String accessdesc;
	
    private Integer createdby;
    
    private Timestamp createdon;
    
    private Integer modifiedby;
    
    private Timestamp modifiedon;
    
    boolean active;
    
    private String createdbyip;
    
    private String modifiedbyip;

	public String getAccessdesc() {
		return accessdesc;
	}

	public void setAccessdesc(String accessdesc) {
		this.accessdesc = accessdesc;
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
	
	

	
}