package com.ppvfra.entity;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "m_districts")
public class Districts {

	
	private int id;
	@Id // one id column is mandatory
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int districtcode; //prinamry key in database
	private String districtname_inenglish;
	private int statecode;  //f key in database
	private int stateid;
	
	private Integer census2001code;
	private int census2011code;
	private int createdby;
	private Timestamp createdon;
	
	
	private Boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}  
	public int getDistrictcode() {
		return districtcode;
	}
	public void setDistrictcode(int districtcode) {
		this.districtcode = districtcode;
	}
	public String getDistrictname_inenglish() {
		return districtname_inenglish;
	}
	public void setDistrictname_inenglish(String districtname_inenglish) {
		this.districtname_inenglish = districtname_inenglish;
	}
	public int getStatecode() {
		return statecode;
	}
	public void setStatecode(int statecode) {
		this.statecode = statecode;
	}
	public int getStateid() {
		return stateid;
	}
	public void setStateid(int stateid) {
		this.stateid = stateid;
	}
	public Integer getCensus2001code() {
		return census2001code;
	}
	public void setCensus2001code(Integer census2001code) {
		this.census2001code = census2001code;
	}
	public int getCensus2011code() {
		return census2011code;
	}
	public void setCensus2011code(int census2011code) {
		this.census2011code = census2011code;
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
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
