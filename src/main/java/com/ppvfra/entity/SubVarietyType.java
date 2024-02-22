package com.ppvfra.entity;

 
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="m_subvarietytype")
public class SubVarietyType
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	
	 
	private int varietyid ;
    private String subvarietycd ;
    private String subvarietytype ;
    private String description ;
    private int createdby ;
    private Date createdon;
    private int modifiedby ; 
    private Date modifiedon;
    private boolean active;
    private String createbyip ;
    private String modifiedbyip;
    private String subvarietycd_hin ;
    private String subvarietytype_hin ;
    private String description_hin;
    private String varietycd_hin;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getVarietyid() {
		return varietyid;
	}
	public void setVarietyid(int varietyid) {
		this.varietyid = varietyid;
	}
	public String getSubvarietycd() {
		return subvarietycd;
	}
	public void setSubvarietycd(String subvarietycd) {
		this.subvarietycd = subvarietycd;
	}
	public String getSubvarietytype() {
		return subvarietytype;
	}
	public void setSubvarietytype(String subvarietytype) {
		this.subvarietytype = subvarietytype;
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
	public String getSubvarietycd_hin() {
		return subvarietycd_hin;
	}
	public void setSubvarietycd_hin(String subvarietycd_hin) {
		this.subvarietycd_hin = subvarietycd_hin;
	}
	public String getSubvarietytype_hin() {
		return subvarietytype_hin;
	}
	public void setSubvarietytype_hin(String subvarietytype_hin) {
		this.subvarietytype_hin = subvarietytype_hin;
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