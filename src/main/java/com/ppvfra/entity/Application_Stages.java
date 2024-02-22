package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_stages")
public class Application_Stages
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
   
	 private String stage;
	    private String status;
	    private String timeline;
	    private Integer createdby;
	    private Timestamp createdon;
	    private Integer modifiedby;
	    private Timestamp modifiedon;
	    private Boolean active;
	    private String createdbyip;
	    private String modifiedbyip;
	    private String stage_hin;
	    private String status_hin;
	    
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getStage() {
			return stage;
		}
		public void setStage(String stage) {
			this.stage = stage;
		}
		 
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getTimeline() {
			return timeline;
		}
		public void setTimeline(String timeline) {
			this.timeline = timeline;
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
		public String getStage_hin() {
			return stage_hin;
		}
		public void setStage_hin(String stage_hin) {
			this.stage_hin = stage_hin;
		}
		public String getStatus_hin() {
			return status_hin;
		}
		public void setStatus_hin(String status_hin) {
			this.status_hin = status_hin;
		}
    
}