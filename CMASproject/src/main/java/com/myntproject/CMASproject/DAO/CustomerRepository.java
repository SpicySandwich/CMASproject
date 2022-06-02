package com.myntproject.CMASproject.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myntproject.CMASproject.Model.Customer;
import com.myntproject.CMASproject.Model.Role;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT u FROM Customer u WHERE u.email = ?1")
	
	Customer findByEmail(String email);
    
    
	

}
