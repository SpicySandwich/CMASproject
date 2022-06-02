package com.myntproject.CMASproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeatureController {
	
	
	@GetMapping("/logoutforall")
	public String logoutAll() {
		
		return "logout_page";
	}

}
