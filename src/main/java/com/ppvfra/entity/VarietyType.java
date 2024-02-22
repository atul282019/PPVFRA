package com.ppvfra.entity;

 
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="m_varietytype")
public class VarietyType
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	
	 
    private String varietycd;
    private String varietytype;
    private String description ;
    private int createdby ;
    private Date createdon;
    private int modifiedby;
    private Date modifiedon;
    private boolean active ;
    private String createbyip;
    private String modifiedbyip ;
    private String varietytype_hin ;
    private String description_hin ;
    private String varietycd_hin ;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVarietycd() {
		return varietycd;
	}
	public void setVarietycd(String varietycd) {
		this.varietycd = varietycd;
	}
	public String getVarietytype() {
		return varietytype;
	}
	public void setVarietytype(String varietytype) {
		this.varietytype = varietytype;
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
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public int getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Date getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCreatebyip() {
		return createbyip;
	}
	public void setCreatebyip(String createbyip) {
		this.createbyip = createbyip;
	}
	public String getModifiedbyip() {
		return modifiedbyip;
	}
	public void setModifiedbyip(String modifiedbyip) {
		this.modifiedbyip = modifiedbyip;
	}
	public String getVarietytype_hin() {
		return varietytype_hin;
	}
	public void setVarietytype_hin(String varietytype_hin) {
		this.varietytype_hin = varietytype_hin;
	}
	public String getDescription_hin() {
		return description_hin;
	}
	public void setDescription_hin(String description_hin) {
		this.description_hin = description_hin;
	}
	public String getVarietycd_hin() {
		return varietycd_hin;
	}
	public void setVarietycd_hin(String varietycd_hin) {
		this.varietycd_hin = varietycd_hin;
	}
	
    
    
}