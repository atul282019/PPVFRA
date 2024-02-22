package com.ppvfra.entity;


import java.io.Serializable;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;




@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class UserAdd implements Serializable{

	
	 
	 

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
	
	 

	  @SuppressWarnings("deprecation")
	 @NotBlank(message="Please Enter Email")
	  @Email
	  @Column(nullable=false, unique=true)
	  private String email;
	
	 
	@NotBlank(message="Please Enter mobile_number")
	@Pattern(regexp="(^$|[0-9]{10})", message="Please Enter Valid pattern Mobile no")
	private String mobile_number;
	
	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	/*
	 * @NotNull
	 * 
	 * @Size(min=1 , max=400)
	 */
	@Column(name="address")
	private String address;
	
	@NotNull
	@Size(min=1,message="Designation cannot be blank")
	@Column(name="designation")
	private String designation;
	
	
	@NotNull
	@Size(min=1,message="Please Select One Role") 
	private String role;
	 
	  
	  public String getRole() { return role; }
	  
	  public void setRole(String role) { this.role = role; }
	 
	
	
	
	/*
	 * private Boolean status;
	 * 
	 * public Boolean getStatus() { return status; }
	 * 
	 * public void setStatus(Boolean status) { this.status = status; }
	 */
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

	 

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public UserAdd() {
		
	}
	 
	@NotBlank
	@Size(min=1,message="First Name Cannot Be Blank")
	private String firstname;
	

	private String middlename;
	
	private String lastname;
	
	//@NotNull
	//@Min(value=1,message="Please Select A State First")
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
		
		private boolean isactive;




		public boolean isIsactive() {
			return isactive;
		}


		public void setIsactive(boolean isactive) {
			this.isactive = isactive;
		}
		
		private Integer office_id;




		public Integer getOffice_id() {
			return office_id;
		}


		public void setOffice_id(Integer office_id) {
			this.office_id = office_id;
		}

		 public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


	 
		 
		@NotNull
		@Size(min=5,message="Password Cannot Be Blank and should be of minimum 7 charachters")
		 private String password;
		    
		  
		  
		 
	
}