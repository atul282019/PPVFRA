package com.ppvfra.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
 
@SuppressWarnings("deprecation")
@Entity
@Table(name="m_company")
public class EditCompanyRegistration{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    @NotNull
    @Size(min=1,max=150,message="Company Name Cannot be blank")
    @Column(name="companyname")
    private String companyname;
    
	/*
	 * @NotNull(message="Year of Incorporation Cannot be blank")
	 * 
	 * @Min(1850)
	 * 
	 * @Max(3000)
	 */
	@Column(name="year_of_incorporation")
    private Integer Year_of_Incorporation;
    
	/*
	 * @NotBlank(message="Acronym Cannot be blank")
	 * 
	 * @Size(max=4)
	 */
	@Column(name="acronym")
    private String acronym;
	
	@NotNull
	@Size(min=1,max=150,message="Cin Number Cannot be blank")
	@Column(name="cin_number")
    private String cin_number;
	
    @NotNull(message="Please select a country")
    @Column(name="seat_country")
    @Min(value=1)
    private Integer seat_Countryid;
  
	/*
	 * @NotNull
	 * 
	 * @Min(value=1,message="Please select a State")
	 */
    private Integer seat_state;
    
	/*
	 * @NotNull
	 * 
	 * @Min(value=1,message="Please select a district")
	 */
    private Integer seat_districts;
    
    @NotBlank(message="City/Town/Village Cannot be blank")
    @Size(min=1,max=150)
    @Column(name="seat_city_village_town")
    private String seat_CityVillageTown;
    
    @NotBlank(message="Enter valid Pincode/ZipCode")
	@Pattern(regexp="(^$|[0-9]{6})")
    @Column(name="seat_pincode")
    private String seat_Pincode;
    
    @NotNull
    @Column(name="registeredoffice_country")
    @Min(value=1,message="Please select country")
    private Integer Registeredoffice_Countryid;
	
    /*
	 * @NotNull
	 * 
	 * @Min(value=1,message="Please select a state first")
	 */
   
    @Column(name="registeredoffice_state")
    private Integer registeredoffice_state;
    
	/*
	 * @NotNull
	 * 
	 * @Min(value=1,message="Please select a district")
	 */
	@Column(name="registeredoffice_districts")
    private Integer registeredoffice_districts;
	
	@NotBlank(message="City/Town/Village Cannot be blank")
	@Size(min=1,max=150)
    @Column(name="registeredoffice_city_village_town")
    private String Registeredoffice_CityVillageTown;
    
	@NotBlank(message="Enter valid Pincode/ZipCode")
    @Column(name="registeredoffice_pincode")
    @Pattern(regexp="(^$|[0-9]{6})")
    private String Registeredoffice_Pincode;
    
	
	  @NotBlank(message="Name Cannot be blank")
	  @Size(min=1,max=100)
	  @Column(name="author_name") 
	  private String Author_Name;
	
	  @NotBlank(message="Designation Cannot be blank")
	  @Size(min=1,max=150)
	  @Column(name="author_designation")
	  private String Author_Designation; 
	
	  @NotBlank(message="Address Cannot be blank")
	  @Size(min=1,max=150)
	  @Column(name="author_address") 
	  private String Author_Address;
	
	  @NotNull
	  @Column(name="author_countryid")
	  @Min(value=1,message="Please select a country")
	  private Integer Author_Countryid;
	

	  private Integer author_statecode;
	
	/*
	 * @NotNull
	 * 
	 * @Min(value=1,message="Please select a district")
	 */
	  @Column(name="author_districtcode") 
	  private Integer author_districtcode;
	
	  @NotBlank(message="City/Town/Village Cannot be blank")
	  @Size(min=1,max=150)
	  @Column(name="author_cityvillagetown") 
	  private String Author_CityVillageTown; 
	
	  @NotBlank(message="Enter valid Pincode/ZipCode")
	  @Pattern(regexp="(^$|[0-9]{6})")
	  @Column(name="author_pincode") 
	  private String Author_Pincode;
	
	  @NotBlank(message="Field Cannot be blank")
	  @Pattern(regexp="(^$|[0-9]{10})", message="Please enter valid Mobile No")
	  @Column(name="author_mobile")
      private String Author_Mobile;
	
	/*
	  private int userid;
	  public int getUserid(){return userid;} 
	  public void setUserid(int userid){this.userid = userid; }
	 */
	  @NotBlank(message="Email Cannot be blank")
	  @Email
	  @Size(min=1,max=150)
	  @Column(name="author_email") 
	  private String Author_Email;
	   
	
	//  @NotBlank
	//  @Size(min=6,max=8)
	 // @Pattern(regexp="(^$|[0-9]{10})", message="Please enter valid Fax No")
	  @Column(name="author_fax") 
	  private String Author_Fax;
	
	private Long createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private String verificationstatus;
	private String verificationremarks;
	private Integer verifiedby;
	private String verifiedbydesignation;
	private String author_mobilecountrycode;
	private String author_faxcountrycode;
	
	private Boolean active;
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	//@NotBlank(message="Username Cannot be blank")
	@Transient
	private String username;
	
	@NotBlank(message="Address Cannot be blank")
	private String seat_address;
	
	@NotBlank(message="Address Cannot be blank")
	private String registered_address;
	
	public String getRegistered_address() {
		return registered_address;
	}
	public void setRegistered_address(String registered_address) {
		this.registered_address = registered_address;
	}
	public String getSeat_address() {
		return seat_address;
	}
	public void setSeat_address(String seat_address) {
		this.seat_address = seat_address;
	}
	
	public Integer getYear_of_Incorporation() {
		return Year_of_Incorporation;
	}
	public void setYear_of_Incorporation(Integer year_of_Incorporation) {
		Year_of_Incorporation = year_of_Incorporation;
	}
	public Integer getSeat_Countryid() {
		return seat_Countryid;
	}
	public void setSeat_Countryid(Integer seat_Countryid) {
		this.seat_Countryid = seat_Countryid;
	}
	
	public Integer getSeat_state() {
		return seat_state;
	}
	public void setSeat_state(Integer seat_state) {
		this.seat_state = seat_state;
	}
	public Integer getSeat_districts() {
		return seat_districts;
	}
	public void setSeat_districts(Integer seat_districts) {
		this.seat_districts = seat_districts;
	}
	public String getSeat_CityVillageTown() {
		return seat_CityVillageTown;
	}
	public void setSeat_CityVillageTown(String seat_CityVillageTown) {
		this.seat_CityVillageTown = seat_CityVillageTown;
	}
	public String getSeat_Pincode() {
		return seat_Pincode;
	}
	public void setSeat_Pincode(String seat_Pincode) {
		this.seat_Pincode = seat_Pincode;
	}
	public Integer getRegisteredoffice_Countryid() {
		return Registeredoffice_Countryid;
	}
	public void setRegisteredoffice_Countryid(Integer registeredoffice_Countryid) {
		Registeredoffice_Countryid = registeredoffice_Countryid;
	}
	
	public String getRegisteredoffice_CityVillageTown() {
		return Registeredoffice_CityVillageTown;
	}
	public void setRegisteredoffice_CityVillageTown(String registeredoffice_CityVillageTown) {
		Registeredoffice_CityVillageTown = registeredoffice_CityVillageTown;
	}
	public String getRegisteredoffice_Pincode() {
		return Registeredoffice_Pincode;
	}
	public void setRegisteredoffice_Pincode(String registeredoffice_Pincode) {
		Registeredoffice_Pincode = registeredoffice_Pincode;
	}
	
	
	public Integer getRegisteredoffice_state() {
		return registeredoffice_state;
	}
	public void setRegisteredoffice_state(Integer registeredoffice_state) {
		this.registeredoffice_state = registeredoffice_state;
	}
	public Integer getRegisteredoffice_districts() {
		return registeredoffice_districts;
	}
	public void setRegisteredoffice_districts(Integer registeredoffice_districts) {
		this.registeredoffice_districts = registeredoffice_districts;
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
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public String getCin_number() {
		return cin_number;
	}
	public void setCin_number(String cin_number) {
		this.cin_number = cin_number;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	
	
	 
	public String getVerificationstatus() {
		return verificationstatus;
	}
	public void setVerificationstatus(String verificationstatus) {
		this.verificationstatus = verificationstatus;
	}
	
	
	
	public String getVerificationremarks() {
		return verificationremarks;
	}
	public void setVerificationremarks(String verificationremarks) {
		this.verificationremarks = verificationremarks;
			}
	 
	public String getVerifiedbydesignation() {
		return verifiedbydesignation;
	}
	public void setVerifiedbydesignation(String verifiedbydesignation) {
		this.verifiedbydesignation = verifiedbydesignation;
	}
	public Integer getVerifiedby() {
		return verifiedby;
	}
	public void setVerifiedby(Integer verifiedby) {
		this.verifiedby = verifiedby;
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

}
