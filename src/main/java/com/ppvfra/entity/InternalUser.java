package com.ppvfra.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.remote.TargetedNotification;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
 
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;



@Entity
@Table(name="users")
public class InternalUser implements Serializable{

	
	 
	 @Id // one id column is mandatory
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	 
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name="name")
	private String name;
	
	  @Column(name="usrname") 
	  private String usrname;
	  
	  
	  public String getUsrname() 
	  { return usrname; }
	 

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	 


	  @SuppressWarnings("deprecation")
	  @NotBlank(message="Please Enter Email")
	  @Email
	  @Column(name="email")
	  private String email;
	
	 
	
	@Column(name="contactno")
	private String contactno;
	
	
	private String mobile_number;
	
	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	@Column(name="address")
	private String address;
	
	@NotNull
	@Size(min=1,message="Designation cannot be blank")
	@Column(name="designation")
	private String designation;
	
	
	@NotNull
	 
	 @Size(min=1,message="Please Select One Role") private String role;
	 
	  
	  public String getRole() { return role; }
	  
	  public void setRole(String role) { this.role = role; }
	 
	
	
	
	private Boolean status;

	 public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public InternalUser() {
		
	}
	 
	@NotNull
	@Size(min=1,message="First Name Cannot Be Blank")
	private String firstname;
	

	private String middlename;
	
	private String lastname;
	
	
	@Column(name="stateid")
	private Integer stateid;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	private Boolean isactive;
	
	
	
	
	public Boolean getIsactive() {
		return isactive;
	}


	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,targetEntity = User_Role.class)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	public Set<User_Role> userrole;




	public Set<User_Role> getUserrole() {
		return userrole;
	}


	public void setUserrole(Set<User_Role> userrole) {
		this.userrole = userrole;
	}
	 
		 private Integer companyid;
		 private Integer instituteid;
		 
	
		 private Integer applicantid;



		public Integer getInstituteid() {
			return instituteid;
		}


		public void setInstituteid(Integer instituteid) {
			this.instituteid = instituteid;
		}


		public Integer getApplicantid() {
			return applicantid;
		}


		public void setApplicantid(Integer applicantid) {
			this.applicantid = applicantid;
		}


		public Integer getCompanyid() {
			return companyid;
		}


		public void setCompanyid(Integer companyid) {
			this.companyid = companyid;
		}
		
		private String fullname;
		
		@NotBlank(message="Please Enter username")
		private String username;
		private int usertypeid;
	


		public String getFullname() {
			return fullname;
		}


		public void setFullname(String fullname) {
			this.fullname = fullname;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}

		public int getUsertypeid() {
			return usertypeid;
		}

		public void setUsertypeid(int usertypeid) {
			this.usertypeid = usertypeid;
		}
		
		private Integer office_id;




		public Integer getOffice_id() {
			return office_id;
		}


		public void setOffice_id(Integer office_id) {
			this.office_id = office_id;
		}

 
	
}