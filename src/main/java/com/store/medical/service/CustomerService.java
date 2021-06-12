package com.store.medical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.medical.entity.Customer;
import com.store.medical.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomService customService;
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll(); 
	}
	
	public Customer getCustomer(int id) {
		return customerRepository.getOne(id);
	}

	public int getNextCustomerId() {
		return customerRepository.getMaxId();
	}

	public boolean addCustomer(Customer customer) {
		Customer cust = customerRepository.saveAndFlush(customer);
		if(cust == null || cust.equals(null))
			return false;
		return true;
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public List<Customer> searchCustomer(String cust_name) {
		if(cust_name != null) {
			return customerRepository.searchCustomer(cust_name);
		}
		return getAllCustomers();
	}

	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}
}
