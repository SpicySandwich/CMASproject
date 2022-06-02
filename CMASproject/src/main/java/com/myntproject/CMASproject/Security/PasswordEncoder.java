package com.myntproject.CMASproject.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		
		String bcryptpassword  = "1234";
		String eccodedpassword = encoder.encode(bcryptpassword);
		
		System.out.println(eccodedpassword);
	}

}
