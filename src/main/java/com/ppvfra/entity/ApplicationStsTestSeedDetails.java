package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="application_sts_test_seed_details")
public class ApplicationStsTestSeedDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int applicationid;
	private int application_sts_test_id;
	private String seed_sent_to_leadcenter;
	private String seed_sent_to_coopcenter;
	private String additional_seed;
	private Date dateof_seadsent;
	private Date dateof_additionalseadsent;
	private String seed_discarded;
	private Date date_of_discard;
	private String reason_of_discard;
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
	public int getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(int applicationid) {
		this.applicationid = applicationid;
	}
	public int getApplication_sts_test_id() {
		return application_sts_test_id;
	}
	public void setApplication_sts_test_id(int application_sts_test_id) {
		this.application_sts_test_id = application_sts_test_id;
	}
	public String getSeed_sent_to_leadcenter() {
		return seed_sent_to_leadcenter;
	}
	public void setSeed_sent_to_leadcenter(String seed_sent_to_leadcenter) {
		this.seed_sent_to_leadcenter = seed_sent_to_leadcenter;
	}
	public String getSeed_sent_to_coopcenter() {
		return seed_sent_to_coopcenter;
	}
	public void setSeed_sent_to_coopcenter(String seed_sent_to_coopcenter) {
		this.seed_sent_to_coopcenter = seed_sent_to_coopcenter;
	}
	public String getAdditional_seed() {
		return additional_seed;
	}
	public void setAdditional_seed(String additional_seed) {
		this.additional_seed = additional_seed;
	}
	public Date getDateof_seadsent() {
		return dateof_seadsent;
	}
	public void setDateof_seadsent(Date dateof_seadsent) {
		this.dateof_seadsent = dateof_seadsent;
	}
	public Date getDateof_additionalseadsent() {
		return dateof_additionalseadsent;
	}
	public void setDateof_additionalseadsent(Date dateof_additionalseadsent) {
		this.dateof_additionalseadsent = dateof_additionalseadsent;
	}
	public String getSeed_discarded() {
		return seed_discarded;
	}
	public void setSeed_discarded(String seed_discarded) {
		this.seed_discarded = seed_discarded;
	}
	public Date getDate_of_discard() {
		return date_of_discard;
	}
	public void setDate_of_discard(Date date_of_discard) {
		this.date_of_discard = date_of_discard;
	} 
	public String getReason_of_discard() {
		return reason_of_discard;
	}
	public void setReason_of_discard(String reason_of_discard) {
		this.reason_of_discard = reason_of_discard;
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