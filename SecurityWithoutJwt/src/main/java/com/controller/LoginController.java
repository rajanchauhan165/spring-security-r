package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.repository.CustomerRepo;

@RestController
public class LoginController {
	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping("/signin")
	public ResponseEntity<Customer> loggedInCustomerDetails(Authentication auth){
		Customer customer = customerRepo.findByEmail(auth.getUsername()).orElseThrow(()-> new BadCredentialsException("Wrong credentials"));
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
}
