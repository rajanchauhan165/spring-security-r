package com.service;
import java.util.List;
import com.exception.CustomerException;
import com.model.Customer;

public interface CustomerService {
	public Customer registerCustomer(Customer customer);
	public Customer getCustomerByEmail(String email)throws CustomerException;
	public List<Customer> getAllCustomer()throws CustomerException;
}
