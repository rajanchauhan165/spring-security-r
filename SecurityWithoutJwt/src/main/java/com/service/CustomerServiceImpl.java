package com.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exception.CustomerException;
import com.model.Customer;
import com.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer registerCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer getCustomerByEmail(String email) throws CustomerException {
		return customerRepo.findByEmail(email).orElseThrow(()->new CustomerException("Customer not found"));
	}

	@Override
	public List<Customer> getAllCustomer() throws CustomerException {
		List<Customer> list = customerRepo.findAll();
		if(list.isEmpty()) {
			throw new CustomerException("No customer found");
		}
		else {
			return list;
		}
	}

}
