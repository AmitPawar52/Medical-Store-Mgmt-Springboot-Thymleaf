package com.store.medical.controller;

/*	Author: Amit Pawar
 * 
 * */



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.medical.entity.Customer;
import com.store.medical.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("")
	public String all(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		model.addAttribute("customersActiveClass", "active");
		return "customers";
	}
	
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		int id = customerService.getNextCustomerId();
		id = id + 1;
		Customer customer = new Customer();
		customer.setId(id);
		model.addAttribute("customer", customer);
		return "addCustomer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		if(customerService.addCustomer(customer)) {
			int id = customerService.getNextCustomerId() + 1;
			Customer c = new Customer();
			c.setId(id);
			model.addAttribute("customer", c);
			model.addAttribute("response", "Customer data saved successfully!");
		}
		return "addCustomer";
	}
	
	@GetMapping("/{id}")
	public String getOneCustomer(@PathVariable("id") int id, Model model) {
		Customer c = customerService.getCustomer(id);
		if(!c.equals(null) || c==null) {
			model.addAttribute("customer", c);
		} else {
			model.addAttribute("error_response", "Error in fetching customer details!");
		}
		return "customer-profile";
	}
	
	@PostMapping("/{id}")
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		
		//System.out.println(customer.getId());
		if(customerService.getCustomer(customer.getId()).equals(customer)) {
			model.addAttribute("response", "Nothing to update");
			model.addAttribute("customer", customer);
			return "customer-profile";
		}
		Customer c = customerService.updateCustomer(customer);
		if(c.equals(customer)) {
			model.addAttribute("customer", c);
			model.addAttribute("response", "data updated successfully");
		}
		return "customer-profile";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@PathParam("cust_search") String cust_search, Model model) {
		model.addAttribute("customers", customerService.searchCustomer(cust_search));
		model.addAttribute("customersActiveClass", "active");
		return "customers";
	}
	
	@InitBinder
	 public void initDateBinder(final WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	 }
}
