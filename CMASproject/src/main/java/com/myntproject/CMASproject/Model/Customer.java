package com.myntproject.CMASproject.Model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "customers")
@DynamicUpdate
public class Customer {
	
	@Id
	@Column(name = "id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
	@Column(name = "email" , nullable = false, unique =  true, length = 30,updatable= false)
	private String email;
	
	//@NotNull 
	@Column(nullable = false,length = 300,updatable= false)
	private String password;
	
	@Size(min = 4, max = 15, message = "Firstname must longer than 4 character but not exceed than 15")
	@Column(nullable = false, length = 15)
	private String firstname;
	
	@Size(min = 4, max = 15, message = "Lastname must longer than 4 character but not exceed than 15")
	@Column(nullable = false, length = 15)
	private String lastname;
	
	@Size(min = 4, message = "Please enter Date of Birth")
	@Column(nullable = false)
	private String dateofbirth;
	
	@Size(min = 4, max = 50, message = "Address must longer than 4 character but not exceed than 50")
	@Column(nullable = false, length = 50)
	private String address;
	
	@Digits(fraction = 0, integer = 2, message = "Choose a role" )
	@Column (nullable = false)
	private Integer fk_role_id;
	
	@ManyToOne
	@JoinColumn(name = "fk_role_id",insertable =  false, updatable = false)		
	private Role role;
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getFk_role_id() {
		return fk_role_id;
	}

	public void setFk_role_id(Integer fk_role_id) {
		this.fk_role_id = fk_role_id;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}

 
