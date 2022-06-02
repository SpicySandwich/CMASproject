package com.myntproject.CMASproject.DAOtest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.annotation.Rollback;

import com.myntproject.CMASproject.DAO.CustomerRepository;
import com.myntproject.CMASproject.Model.Customer;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void TestCreateCustomer() {
		
		Customer customer = new Customer();
		customer.setEmail("conredalas1@gmail.com");
		customer.setPassword("11234");
		customer.setFirstname("conred");
		customer.setLastname("alas-as");
		customer.setDateofbirth("03/03/1995");
		customer.setAddress("Makati");
		customer.setFk_role_id(1);
		
				  
		
		Customer saveCustomer= repo.save(customer);
		
		Customer existCustomer = entityManager.find(Customer.class, saveCustomer.getId());
		
		assertThat(existCustomer.getEmail()).isEqualTo(customer.getEmail());
	
	}
	
	@Test
	public void  testFindCustomerEmail() {
		
		String email = "conred@gmail.com";
		
		Customer customer = repo.findByEmail(email);
		
		assertThat(customer).isNotNull();
		
	}

}
