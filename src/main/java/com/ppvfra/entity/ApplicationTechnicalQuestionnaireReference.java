package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="application_technical_questionnaire_references")
public class ApplicationTechnicalQuestionnaireReference {
	
		@Id 
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private int applicationid;
		private int applicationtqid;
		private String technical_questionnaire_reference;
		private Boolean active;
		private int createdby;
		private Timestamp createdon;
		private int modifiedby;
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
		public int getApplicationtqid() {
			return applicationtqid;
		}
		public void setApplicationtqid(int applicationtqid) {
			this.applicationtqid = applicationtqid;
		}
		public String getTechnical_questionnaire_reference() {
			return technical_questionnaire_reference;
		}
		public void setTechnical_questionnaire_reference(String technical_questionnaire_reference) {
			this.technical_questionnaire_reference = technical_questionnaire_reference;
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
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
