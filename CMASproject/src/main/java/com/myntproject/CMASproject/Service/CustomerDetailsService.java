package com.myntproject.CMASproject.Service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.management.relation.Role;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myntproject.CMASproject.DAO.CustomerRepository;
import com.myntproject.CMASproject.DAO.RoleRepository;
import com.myntproject.CMASproject.Model.Customer;

@Service

@Transactional
public class CustomerDetailsService implements UserDetailsService, CustomerInterface {

	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private RoleRepository repo2;
	
	
	
	 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
	Customer customer = repo.findByEmail(email);
	CustomeCustomerDetails customeCustomerDetails = new CustomeCustomerDetails(customer);
	
	if(customer == null) {
		
		throw new UsernameNotFoundException("Email not found");
		
	}
		return customeCustomerDetails;
	}
	
	public void deletedataByID(long id) {
		this.repo.deleteById(id);
		
	}
	
	@Override
	public Customer getCustomerbyId(long id) {
		
		   Optional<Customer> optional = repo.findById(id);
		   Customer customer = null;
		   
		    if(optional.isPresent()){
		    	
		     customer = optional.get();
		     
		    }else{
		     throw new RuntimeException("Customer not found " + id);
		       }
		    return customer;
				
			}
	
	//-----------------------------------------
		//update in process
	
//	public void signupCustomer(Customer customer) {
//		
//		this.repo.save(customer);
//			
//		}
	
	@Transactional
	public Customer signupCustomer(Customer newUser) {
		
		Customer currentUser = repo.getById(newUser.getId());
	
		
	    currentUser.setFirstname(newUser.getFirstname());
	    currentUser.setLastname(newUser.getLastname());
	    currentUser.setDateofbirth(newUser.getDateofbirth());
	    currentUser.setAddress(newUser.getAddress());
	    currentUser.setFk_role_id(newUser.getFk_role_id());
	    
	 
	    
	    
	    
	    return repo.save(currentUser);
	}
	
	//--------------------
	//role
	
	public Customer addCustomer(Customer customer) {
		
		return repo.save(customer);
	}
	//-----------------------------------
	
}
