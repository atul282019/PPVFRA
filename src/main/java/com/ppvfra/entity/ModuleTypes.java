package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_module_types")
public class ModuleTypes {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	 	private int Id;
		private String moduletype;
	    private int createdby;
	    private Timestamp createdon;
	    private String modifiedby;
	    private Timestamp modifiedon;
	    private Boolean active;
	    private String createdbyip;
	    private String modifiedbyip;
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getModuletype() {
			return moduletype;
		}
		public void setModuletype(String moduletype) {
			this.moduletype = moduletype;
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
		public String getModifiedby() {
			return modifiedby;
		}
		public void setModifiedby(String modifiedby) {
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
		
		@Override
		public String toString() {
			return "ModuleTypes [Id=" + Id + ", moduletype=" + moduletype + ", createdby=" + createdby + ", createdon="
					+ createdon + ", modifiedby=" + modifiedby + ", modifiedon=" + modifiedon + ", active=" + active
					+ ", createdbyip=" + createdbyip + ", modifiedbyip=" + modifiedbyip + "]";
		}
    }
