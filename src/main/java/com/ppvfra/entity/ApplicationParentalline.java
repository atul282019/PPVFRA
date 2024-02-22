package com.ppvfra.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_parentalline")
public class ApplicationParentalline {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
		private int applicationid;
		private String parental_line;
		private String denominations;
		private String source;
		private String authorised_letter_obtained;
		private int createdby;
		private Date createdon;
		private int modifiedby;
		private Date modifiedon;
		private String createdbyip;
		private String modifiedbyip;
		private Boolean active;
		private String record_language;
		private int typeline;
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
		public String getParental_line() {
			return parental_line;
		}
		public void setParental_line(String parental_line) {
			this.parental_line = parental_line;
		}
		public String getDenominations() {
			return denominations;
		}
		public void setDenominations(String denominations) {
			this.denominations = denominations;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getAuthorised_letter_obtained() {
			return authorised_letter_obtained;
		}
		public void setAuthorised_letter_obtained(String authorised_letter_obtained) {
			this.authorised_letter_obtained = authorised_letter_obtained;
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
		public String getRecord_language() {
			return record_language;
		}
		public void setRecord_language(String record_language) {
			this.record_language = record_language;
		}
		public int getTypeline() {
			return typeline;
		}
		public void setTypeline(int typeline) {
			this.typeline = typeline;
		}
	
}
