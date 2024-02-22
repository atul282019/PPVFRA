package com.ppvfra.entity;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="m_office") 
public class OfficeDetails {
	

	@Id // one id column is mandatory
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@NotNull
	@Size(min=1,message="Field cannot be blank")
	private String office_type;
	
	@NotNull
	@Size(min=1,message="Field cannot be blank")
	private String office_code;
	
	@NotNull
	@Size(min=1,message="Field cannot be blank")
	private String location;
	
	@NotNull
	@Size(min=1,message="Field cannot be blank")
	private String address;
	
	@NotNull	
	@Min(value=1,message="Please select a state")
	private Integer state ;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{6})", message="Please enter a valid code pattern")
	private String pincode;
	
	@NotNull	
	@Min(value=1,message="Please select a district")
	@Column(name="district_id")
	private Long District_Id;
	
	@NotNull
	@Size(min=1,message="Field cannot be blank")
	private String City;
	
	private Integer createdby;
	private Timestamp createdon;
	private Long modifiedby;
	private Timestamp modifiedon;
	private String createdbyip;
	private String modifiedbyip;
	private Boolean active;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public Long getDistrict_Id() {
		return District_Id;
	}
	public void setDistrict_Id(Long district_Id) {
		District_Id = district_Id;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getOffice_type() {
		return office_type;
	}
	public void setOffice_type(String office_type) {
		this.office_type = office_type;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}    

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	@Required
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	
	/*
	 * @OneToMany(mappedBy="officedetails",cascade=CascadeType.ALL,fetch=FetchType.
	 * LAZY) private Set<OfficeStates> officestates; public Set<OfficeStates>
	 * getOfficestates() { return officestates; } public void
	 * setOfficestates(Set<OfficeStates> officestates) { this.officestates =
	 * officestates; }
	 */

	@OneToMany(fetch = FetchType.LAZY,targetEntity=OfficeStates.class ,cascade= {CascadeType.ALL})
	@JoinColumn(name = "officeid",referencedColumnName="id")
	private Set<OfficeStates> officestate;
	public Set<OfficeStates> getOfficestate() {
		return officestate;
	}
	public void setOfficestate(Set<OfficeStates> officestate) {
		this.officestate = officestate;
	}

	


}