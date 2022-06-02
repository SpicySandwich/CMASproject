package com.myntproject.CMASproject.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myntproject.CMASproject.Model.Customer;

public class CustomeCustomerDetails implements UserDetails {

	private Customer customer;
	
	
//	@OneToMany(mappedBy = "fk_role_id")
//	private List<Customer> senderMessage = new ArrayList<>();
//	
	
	public CustomeCustomerDetails(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_" + customer.getRole().getRolename()));
		return roles;
	}

	@Override
	public String getPassword() {
	
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
	
		return customer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return true;
	}
	
	public String getFullName() {
		
		return customer.getFirstname() + " " + customer.getLastname();
	}
	
	public String getUSerEmail() {
		
		return customer.getEmail();
		
	}
    public String getUSerAddress() {
		
    	return customer.getAddress();
		
	}
     public String getUSerDOB() {
	
    	 return customer.getDateofbirth();

	
}

}
