package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.repository.CustomerRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> optional = customerRepo.findByEmail(username);
		if(optional.isPresent()) {
			Customer customer = optional.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			return new User(customer.getEmail(),customer.getPassword(),authorities);
		}
		else {
			throw new BadCredentialsException("user details not found");
		}
		
		
	}

}
