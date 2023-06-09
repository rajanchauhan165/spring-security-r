package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/test")
	public String test() {
		return "Welcome to Spring Security";
	}
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		customer.setPassword( passwordEncoder.encode(customer.getPassword()) );
		Customer savedCustomer = customerService.registerCustomer(customer);
		return new ResponseEntity<Customer>(savedCustomer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email){
		Customer customer = customerService.getCustomerByEmail(email);
		return new ResponseEntity<Customer>(customer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		List<Customer> list = customerService.getAllCustomer();
		return new ResponseEntity<List<Customer>>(list,HttpStatus.ACCEPTED);
	}
}
