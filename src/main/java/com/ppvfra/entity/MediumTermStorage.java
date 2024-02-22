package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_mts_details")
public class MediumTermStorage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Long applicationid;
	private int type_line;
	private Date moisture_tobe_checked_on;
	private String viability;
	private String moisture;
	private String weight;
	private String seeds_discarded;
	private Date date_of_discard;
	private String reasons;
	private String checked_by;
	private String postregistration;
	private String handedoverto;
	private int no_of_packets;
	private Date handingoverdate;
	private String handingover_remarks;
	private int createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private Boolean active;
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
	public int getType_line() {
		return type_line;
	}
	public void setType_line(int type_line) {
		this.type_line = type_line;
	}
	public Date getMoisture_tobe_checked_on() {
		return moisture_tobe_checked_on;
	}
	public void setMoisture_tobe_checked_on(Date moisture_tobe_checked_on) {
		this.moisture_tobe_checked_on = moisture_tobe_checked_on;
	}
	public String getViability() {
		return viability;
	}
	public void setViability(String viability) {
		this.viability = viability;
	}
	public String getMoisture() {
		return moisture;
	}
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getSeeds_discarded() {
		return seeds_discarded;
	}
	public void setSeeds_discarded(String seeds_discarded) {
		this.seeds_discarded = seeds_discarded;
	}
	public Date getDate_of_discard() {
		return date_of_discard;
	}
	public void setDate_of_discard(Date date_of_discard) {
		this.date_of_discard = date_of_discard;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public String getChecked_by() {
		return checked_by;
	}
	public void setChecked_by(String checked_by) {
		this.checked_by = checked_by;
	}
	public String getPostregistration() {
		return postregistration;
	}
	public void setPostregistration(String postregistration) {
		this.postregistration = postregistration;
	}
	public String getHandedoverto() {
		return handedoverto;
	}
	public void setHandedoverto(String handedoverto) {
		this.handedoverto = handedoverto;
	}
	public int getNo_of_packets() {
		return no_of_packets;
	}
	public void setNo_of_packets(int no_of_packets) {
		this.no_of_packets = no_of_packets;
	}
	public Date getHandingoverdate() {
		return handingoverdate;
	}
	public void setHandingoverdate(Date handingoverdate) {
		this.handingoverdate = handingoverdate;
	}
	public String getHandingover_remarks() {
		return handingover_remarks;
	}
	public void setHandingover_remarks(String handingover_remarks) {
		this.handingover_remarks = handingover_remarks;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

}