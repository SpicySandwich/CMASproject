package com.myntproject.CMASproject.Controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.myntproject.CMASproject.Model.Customer;
import com.myntproject.CMASproject.Service.CustomeCustomerDetails;
import com.myntproject.CMASproject.Service.CustomerDetailsService;

@Controller
public class UserController {
	
	
	

	@Autowired
	private CustomerRepository repo;
	
	
	@Autowired
	private CustomerDetailsService service;
	
	@Autowired
	private CustomerRepository repo2;
	
	@GetMapping("/lazardo.com")
	public String userhomepage() {
		
		return "user_page";
	}
		  
		  @GetMapping("/user_details")
		    public String viewUserAccountForm(
		        @AuthenticationPrincipal CustomeCustomerDetails userDetails,Model model) {
		        String userEmail = userDetails.getUsername();
		        Customer user = repo2.findByEmail(userEmail);
		         
		        model.addAttribute("customer", user);
		    
		         
		        return "user_detail_page";
		    }
	

	@GetMapping("/userlogin")
	public String login() {

		return "login";
	}
	
	@GetMapping("/register_action_user")
	public String register(Model model) {
		
		model.addAttribute("customer", new Customer());
		return "user_register_page";
	}
	
	
	@RequestMapping(path = "/register_action_process_user", method = RequestMethod.POST)
	public String registerprocess(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult) {
		
	if(bindingResult.hasErrors()) {
			
			return "user_register_page";
			
		}else {
			
			BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
			String  encodepassword =encoder.encode(customer.getPassword());
			customer.setPassword(encodepassword);
			repo.save(customer);
		}
		
		return "user_successfully_register_page";
	}
	
	//----
	//update in process
	
	@PostMapping("/forupdateuser")
	public String forupdatedata(@ModelAttribute("customer")Customer customer) {

		service.signupCustomer(customer);
		return "redirect:/user_details";
		
	}
	
	@GetMapping("/updateuser/{id}")
	public String updatecustomerform(@PathVariable(value = "id")long id, Model data){

	Customer customer = service.getCustomerbyId(id);

	data.addAttribute("customer", customer);
	return "user_detail_page";
	
	}
	
	

}
