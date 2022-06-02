package com.myntproject.CMASproject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myntproject.CMASproject.DAO.CustomerRepository;
import com.myntproject.CMASproject.DAO.RoleRepository;
import com.myntproject.CMASproject.Model.Customer;
import com.myntproject.CMASproject.Service.CustomerDetailsService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private CustomerDetailsService service;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/admin.com")
	public String homepage() {
		
		return "index";
	}
	
	@GetMapping("/loginadmin")
	public String login() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//			
//			return "login";
//		}
		return "login";
	}
	
	
	@GetMapping("/register_action")
	public String register(Model model) {
		
		model.addAttribute("customer", new Customer());
		return "registerpage";
	}
	
	//@PostMapping("/register_action_process")
	
	@RequestMapping(path = "/register_action_process", method = RequestMethod.POST)
	public String registerprocess(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult) {
		
		

		if(bindingResult.hasErrors()) {
			
			return "registerpage";
			
		}else {
			
			BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
			String  encodepassword =encoder.encode(customer.getPassword());
			customer.setPassword(encodepassword);
			repo.save(customer);
		}
		
		
		
		return "register_save_page";
	}
	
	@GetMapping("/list_customer")
	public String viewCustomerList(Model model) {
		
		List<Customer> listcustomer = repo.findAll();
		model.addAttribute("listcustomer", listcustomer);
		
		return "customer";
	}
	
	@GetMapping("/deletedata/{id}")
	public String deletedata(@PathVariable (value = "id")long id) {
		this.service.deletedataByID(id);
		
		return "redirect:/list_customer";
		
	}
	
	
	@PostMapping("/forupdatedata")
	public String forupdatedata(@ModelAttribute("customer")Customer customer) {

		//save employee to database
		service.signupCustomer(customer);
		return "redirect:/list_customer";
		
	}
	
	@GetMapping("/updatecustomerform/{id}")
	public String updatecustomerform(@PathVariable(value = "id")long id, Model data){

	Customer customer = service.getCustomerbyId(id);

	data.addAttribute("customer", customer);
	return "update_page";
	
	}
	
	

	
	
	
	
	
	
	

}
