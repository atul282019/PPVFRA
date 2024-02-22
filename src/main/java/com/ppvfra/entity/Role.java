package com.ppvfra.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;


@Entity
@Table(name = "m_roles")
public class Role {
	
	 	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    @SuppressWarnings("deprecation")
		@Column(nullable = false, unique = true)
	    @NotEmpty
	    public String name;

	    @ManyToMany(mappedBy = "roles")
	    private List < User > users;

	    public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List < User > getUsers() {
	        return users;
	    }

	    public void setUsers(List < User > users) {
	        this.users = users;
	    }

	    public String getDescription() {
			return description;
		}
		
		@Required
		public void setDescription(String description) {
			this.description = description;
		}

		 
		
		@Size(min=1,message="Field Cannot be blank")
		private String role;
		
		@NotNull
		@Size(min=1 , max=400)
		private String description;
		
		
		private String status;
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public String description_hin;

		public String getDescription_hin() {
			return description_hin;
		}

		public void setDescription_hin(String description_hin) {
			this.description_hin = description_hin;
		}

		 

		 
		
	    
	    
}
