package com.ppvfra.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.Email;

 
@SuppressWarnings("deprecation")
@Entity
@Table(name="m_institution")
 

public class EditInstitutionRegistration {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
    private int id;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    
      @NotBlank(message="Institute Name Cannot be blank")
      @Column(name="institutename")
      private String InstituteName;
      
      @NotBlank(message="Abbreviation Cannot be blank")
      @Column(name="abbreviation")
      private String abbreviation;
      
	  private String author_mobilecountrycode;
	  
      private String author_faxcountrycode;
      
      @NotBlank(message="Place Cannot be blank")
      @Column(name="place")
      private String place;
      
      @NotNull
      @Column(name="inst_typeid")
      @Min(value=1,message="Please Select Type")
      private Integer Type;

	  
	  @NotBlank(message="Author_Name Cannot be blank")
	  @Column(name="author_name") 
	  private String Author_Name;
	
	  @NotBlank(message="Author Designation Cannot be blank")
	  @Column(name="author_designation")
	  private String Author_Designation;
	
	  @NotBlank(message="Author_Address Cannot be blank")
	  @Column(name="author_address") 
	  private String Author_Address;
	
	  @NotNull
	  @Column(name="author_countryid")
	  @Min(value=1,message="Please select a country")
	  private Integer Author_Countryid;
	
	  @NotNull
	  @Column(name="author_statecode") 
	  @Min(value=1,message="Please select State")
	  private Integer author_statecode;
	
	  @NotNull
	  @Column(name="author_districtcode") 
	  @Min(value=1,message="Please select District")
	  private Integer author_districtcode;
	
	  @NotBlank(message="Field Cannot be blank")
	  @Column(name="author_cityvillagetown") 
	  private String Author_CityVillageTown; 
	
	 // @NotBlank(message="Pincode Cannot be blank")
	  @NotNull
	  @Size(min=6, max=6)
	  @Pattern(regexp="(^$|[0-9]{6})", message="Please enter valid code pattern")
	  @Column(name="author_pincode") 
	  private String Author_Pincode;
	
	  //@NotBlank(message="Mobile Cannot be blank")
	  @NotNull
	  @Size(min=10, max=10)
	  @Pattern(regexp="(^$|[0-9]{10})", message="Please enter valid code pattern")
	  @Column(name="author_mobile")
      private String Author_Mobile;
	  

	  @Email
	  @NotBlank(message="email Cannot be blank.")
	  @Column(name="author_email") 
	  private String Author_Email;
	   
	  @NotBlank(message="Fax no Cannot be blank.")
	  @Column(name="author_fax") 
	  private String Author_Fax;
	  
	 // @NotBlank(message="Username Cannot be blank")
	  @Transient
	  private String username;

	  
	  	private String verifiedbydesignation;
		private String verificationstatus;
		
		private Long createdby;
		private Timestamp createdon;
		private Long modifiedby;
		private Timestamp modifiedon;
		private String createdbyip;
		private String modifiedbyip;
		private String verificationremarks;
		private Integer verifiedby;
		private Boolean active;
	/*
	 * private transient String mobile_number;
	 * 
	 * public String getMobile_number() { return mobile_number; }
	 * 
	 * 
	 * public void setMobile_number(String mobile_number) { this.mobile_number =
	 * mobile_number; }
	 */
		public String getInstituteName() {
			return InstituteName;
		}
		public void setInstituteName(String instituteName) {
			InstituteName = instituteName;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		public Integer getType() {
			return Type;
		}
		public void setType(Integer type) {
			Type = type;
		}
		public String getAuthor_Name() {
			return Author_Name;
		}
		public void setAuthor_Name(String author_Name) {
			Author_Name = author_Name;
		}
		public String getAuthor_Designation() {
			return Author_Designation;
		}
		public void setAuthor_Designation(String author_Designation) {
			Author_Designation = author_Designation;
		}
		public String getAuthor_Address() {
			return Author_Address;
		}
		public void setAuthor_Address(String author_Address) {
			Author_Address = author_Address;
		}
		public Integer getAuthor_Countryid() {
			return Author_Countryid;
		}
		public void setAuthor_Countryid(Integer author_Countryid) {
			Author_Countryid = author_Countryid;
		}
		
		
		
		public Integer getAuthor_statecode() {
			return author_statecode;
		}
		public void setAuthor_statecode(Integer author_statecode) {
			this.author_statecode = author_statecode;
		}
		public Integer getAuthor_districtcode() {
			return author_districtcode;
		}
		public void setAuthor_districtcode(Integer author_districtcode) {
			this.author_districtcode = author_districtcode;
		}
		public String getAuthor_CityVillageTown() {
			return Author_CityVillageTown;
		}
		public void setAuthor_CityVillageTown(String author_CityVillageTown) {
			Author_CityVillageTown = author_CityVillageTown;
		}
		public String getAuthor_Pincode() {
			return Author_Pincode;
		}
		public void setAuthor_Pincode(String author_Pincode) {
			Author_Pincode = author_Pincode;
		}
		public String getAuthor_Mobile() {
			return Author_Mobile;
		}
		public void setAuthor_Mobile(String author_Mobile) {
			Author_Mobile = author_Mobile;
		}
		public String getAuthor_Email() {
			return Author_Email;
		}
		public void setAuthor_Email(String author_Email) {
			Author_Email = author_Email;
		}
		public String getAuthor_Fax() {
			return Author_Fax;
		}
		public void setAuthor_Fax(String author_Fax) {
			Author_Fax = author_Fax;
		}
	   public String getAbbreviation() {
			return abbreviation;
		}
		public void setAbbreviation(String abbreviation) {
			this.abbreviation = abbreviation;
		}
		public String getAuthor_mobilecountrycode() {
			return author_mobilecountrycode;
		}
		public void setAuthor_mobilecountrycode(String author_mobilecountrycode) {
			this.author_mobilecountrycode = author_mobilecountrycode;
		}
		public String getAuthor_faxcountrycode() {
			return author_faxcountrycode;
		}
		public void setAuthor_faxcountrycode(String author_faxcountrycode) {
			this.author_faxcountrycode = author_faxcountrycode;
		}
		 public String getUsername() {
				return username;
		}
			public void setUsername(String username) {
				this.username = username;
		}
			public String getVerifiedbydesignation() {
				return verifiedbydesignation;
			}
			public void setVerifiedbydesignation(String verifiedbydesignation) {
				this.verifiedbydesignation = verifiedbydesignation;
			}
			public String getVerificationstatus() {
				return verificationstatus;
			}
			public void setVerificationstatus(String verificationstatus) {
				this.verificationstatus = verificationstatus;
			}
			public Long getCreatedby() {
				return createdby;
			}
			public void setCreatedby(Long createdby) {
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
			public String getVerificationremarks() {
				return verificationremarks;
			}
			public void setVerificationremarks(String verificationremarks) {
				this.verificationremarks = verificationremarks;
			}
			public Integer getVerifiedby() {
				return verifiedby;
			}
			public void setVerifiedby(Integer verifiedby) {
				this.verifiedby = verifiedby;
			}
			
			
			public Boolean getActive() {
				return active;
			}
			public void setActive(Boolean active) {
				this.active = active;
			}
}
