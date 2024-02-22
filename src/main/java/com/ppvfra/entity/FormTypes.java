package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_formtypes")
public class FormTypes {
	
	 
		@Id // one id column is mandatory
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		public Integer id;
		
		private String formtypename;
		private String formtype_desc;
		private Boolean active;
		private Integer createdby;
		private Timestamp createdon;
		private Integer modifiedby;
		private Timestamp modifiedon;
		private String createdbyip;
		private String modifiedbyip;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getFormtypename() {
			return formtypename;
		}
		public void setFormtypename(String formtypename) {
			this.formtypename = formtypename;
		}
		public String getFormtype_desc() {
			return formtype_desc;
		}
		public void setFormtype_desc(String formtype_desc) {
			this.formtype_desc = formtype_desc;
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
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
