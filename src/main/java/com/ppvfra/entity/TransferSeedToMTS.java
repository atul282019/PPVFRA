package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_transfer_mts")
public class TransferSeedToMTS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long applicationid;
	private double moisture_ptg;
	private double germination_ptg;
	private double purity_ptg;
	private Date seed_transfer_date;
	private String cell_location;
	private String transfer_by;
	private String seed_status;
	private String remarks;
	private int type_line;
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
	
	public long getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(long applicationid) {
		this.applicationid = applicationid;
	}
	 
	 
	 
	public double getMoisture_ptg() {
		return moisture_ptg;
	}
	public void setMoisture_ptg(double moisture_ptg) {
		this.moisture_ptg = moisture_ptg;
	}
	public double getGermination_ptg() {
		return germination_ptg;
	}
	public void setGermination_ptg(double germination_ptg) {
		this.germination_ptg = germination_ptg;
	}
	public double getPurity_ptg() {
		return purity_ptg;
	}
	public void setPurity_ptg(double purity_ptg) {
		this.purity_ptg = purity_ptg;
	}
	
	public Date getSeed_transfer_date() {
		return seed_transfer_date;
	}
	public void setSeed_transfer_date(Date seed_transfer_date) {
		this.seed_transfer_date = seed_transfer_date;
	}
	public String getCell_location() {
		return cell_location;
	}
	public void setCell_location(String cell_location) {
		this.cell_location = cell_location;
	}
	public String getTransfer_by() {
		return transfer_by;
	}
	public void setTransfer_by(String transfer_by) {
		this.transfer_by = transfer_by;
	}
	public String getSeed_status() {
		return seed_status;
	}
	public void setSeed_status(String seed_status) {
		this.seed_status = seed_status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getType_line() {
		return type_line;
	}
	public void setType_line(int type_line) {
		this.type_line = type_line;
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
	
	

}
