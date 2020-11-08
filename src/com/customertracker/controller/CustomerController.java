package com.customertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customertracker.entity.Customer;
import com.customertracker.services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired // this will fetch the implementation of the customer DAO
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		
		//fetch the customer list from customer Service implementation
		List<Customer> customers = customerService.getCustomers();
		
		//add the list to the model attribute
		theModel.addAttribute("customers",customers);
		
		return "list-customers";
	}
	
	@GetMapping("/addCustomerForm")
	public String addCustomerForm(Model theModel) {
		Customer customer = new Customer();
		
		theModel.addAttribute("customer",customer);
	
		return "customer-form";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		
	
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormUpdate")
	public String showFormUpdate(@RequestParam("customerId") int id,Model modal) {
		//get the customer for that ID
		Customer customer = customerService.getCustomer(id);
		
		// add the customer to the model attribute
		modal.addAttribute("customer",customer);
		
		//return the customer form
		
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id, Model model) {
		 customerService.deleteCustomer(id);
		//return the customer list
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchName") String name, Model model) {
		List<Customer> customers =  customerService.searchCustomer(name);
		
		//return the customer list
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
}