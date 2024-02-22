package com.ppvfra.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="m_documentchecklist")
public class DocumentChecklist {
	 @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer id;
	    @NotNull
	    @Min(value=1,message="Please select type of form")
	    private Integer formtypeid;
	    @NotNull
	    @Size(min=1,message="Description cannot be blank")
	    private String document_desc;    
	    private Integer attachment_maximum_size_in_kb;
	    @NotNull
	    @Size(min=1,message="Please select the type of attachment")
	    private String attachment_allow_extension;
	    @NotNull
	    @Size(min=1,message="Please select a option")
	    private String is_mandatory;
	    private Integer createdby;
		private Timestamp createdon;
		private Long modifiedby;
		private Timestamp modifiedon;
		private String createdbyip;
		private String modifiedbyip;
		private Boolean active;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getFormtypeid() {
			return formtypeid;
		}
		public void setFormtypeid(Integer formtypeid) {
			this.formtypeid = formtypeid;
		}
		public String getDocument_desc() {
			return document_desc;
		}
		public void setDocument_desc(String document_desc) {
			this.document_desc = document_desc;
		}
		public Integer getAttachment_maximum_size_in_kb() {
			return attachment_maximum_size_in_kb;
		}
		public void setAttachment_maximum_size_in_kb(Integer attachment_maximum_size_in_kb) {
			this.attachment_maximum_size_in_kb = attachment_maximum_size_in_kb;
		}
		public String getAttachment_allow_extension() {
			return attachment_allow_extension;
		}
		public void setAttachment_allow_extension(String attachment_allow_extension) {
			this.attachment_allow_extension = attachment_allow_extension;
		}
		public String getIs_mandatory() {
			return is_mandatory;
		}
		public void setIs_mandatory(String is_mandatory) {
			this.is_mandatory = is_mandatory;
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
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
		}
		
		
}
