package com.ppvfra.entity;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_sts_seedrecieved")
public class ApplicationStsSeedrecieved
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
   
	private Long applicationid;
    private Date receiptdate ;
    private String seed_req_asper_guideline ;
    private int quantityofseed_recd ;
    private int no_of_packets ;
    private int moisture_ptg ;
    private int germination_ptg ;
    private int purity_ptg ;
    private int cellno ;
    private Long checkedby ;
    private String seed_left ;
    private String finalpackets_sent ;
    private String packets_left ;
    private Long createdby ;
    private Date createdon ;
    private String createdbyip; 
    private Long modifiedby ;
    private Date modifiedon ;
    private String modifiedbyip ;
    private boolean active ;
    private String record_language ;
    private String type_line ;
    public String getType_line() {
		return type_line;
	}
	public void setType_line(String type_line) {
		this.type_line = type_line;
	}
	private int approx_weight;
    private String seed_testing_report_name ;
    private String seed_testing_report_path ;
    private String seedorplant;
    private String remarks;
	
    public String getSeedorplant() {
		return seedorplant;
	}
	public void setSeedorplant(String seedorplant) {
		this.seedorplant = seedorplant;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(Long applicationid) {
		this.applicationid = applicationid;
	}
	public Date getReceiptdate() {
		return receiptdate;
	}
	public void setReceiptdate(Date receiptdate) {
		this.receiptdate = receiptdate;
	}
	public String getSeed_req_asper_guideline() {
		return seed_req_asper_guideline;
	}
	public void setSeed_req_asper_guideline(String seed_req_asper_guideline) {
		this.seed_req_asper_guideline = seed_req_asper_guideline;
	}
	public int getQuantityofseed_recd() {
		return quantityofseed_recd;
	}
	public void setQuantityofseed_recd(int quantityofseed_recd) {
		this.quantityofseed_recd = quantityofseed_recd;
	}
	public int getNo_of_packets() {
		return no_of_packets;
	}
	public void setNo_of_packets(int no_of_packets) {
		this.no_of_packets = no_of_packets;
	}
	public int getMoisture_ptg() {
		return moisture_ptg;
	}
	public void setMoisture_ptg(int moisture_ptg) {
		this.moisture_ptg = moisture_ptg;
	}
	public int getGermination_ptg() {
		return germination_ptg;
	}
	public void setGermination_ptg(int germination_ptg) {
		this.germination_ptg = germination_ptg;
	}
	public int getPurity_ptg() {
		return purity_ptg;
	}
	public void setPurity_ptg(int purity_ptg) {
		this.purity_ptg = purity_ptg;
	}
	public int getCellno() {
		return cellno;
	}
	public void setCellno(int cellno) {
		this.cellno = cellno;
	}
	public Long getCheckedby() {
		return checkedby;
	}
	public void setCheckedby(Long checkedby) {
		this.checkedby = checkedby;
	}
	public String getSeed_left() {
		return seed_left;
	}
	public void setSeed_left(String seed_left) {
		this.seed_left = seed_left;
	}
	public String getFinalpackets_sent() {
		return finalpackets_sent;
	}
	public void setFinalpackets_sent(String finalpackets_sent) {
		this.finalpackets_sent = finalpackets_sent;
	}
	public String getPackets_left() {
		return packets_left;
	}
	public void setPackets_left(String packets_left) {
		this.packets_left = packets_left;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRecord_language() {
		return record_language;
	}
	public void setRecord_language(String record_language) {
		this.record_language = record_language;
	}
	 
	public int getApprox_weight() {
		return approx_weight;
	}
	public void setApprox_weight(int approx_weight) {
		this.approx_weight = approx_weight;
	}
	public String getSeed_testing_report_name() {
		return seed_testing_report_name;
	}
	public void setSeed_testing_report_name(String seed_testing_report_name) {
		this.seed_testing_report_name = seed_testing_report_name;
	}
	public String getSeed_testing_report_path() {
		return seed_testing_report_path;
	}
	public void setSeed_testing_report_path(String seed_testing_report_path) {
		this.seed_testing_report_path = seed_testing_report_path;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 
	 
   
    
    
}