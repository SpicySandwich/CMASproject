package com.myntproject.CMASproject.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myntproject.CMASproject.Service.CustomerDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public  UserDetailsService userDetailsService() {
		
		return new CustomerDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public  DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 authProvider.setUserDetailsService(userDetailsService());
	 authProvider.setPasswordEncoder(passwordEncoder());
	 
	 return authProvider;
	 
			
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
	
		.antMatchers(
				"/list_customer",
				"/register_action",
				"/loginadmin"
				).hasAnyRole("ADMIN")
		
		.antMatchers(
			//	"/register_action_user",
				"/userlogin"
				).hasAnyRole("CUSTOMER")
		
		.anyRequest().permitAll()
		.and()
		.formLogin()
		//.loginPage("/login")
		.usernameParameter("email")
		.defaultSuccessUrl("/user_details")
		.permitAll()
		.and()
		.logout().logoutSuccessUrl("/logoutforall").permitAll();
		
		
	}
	
	

}
