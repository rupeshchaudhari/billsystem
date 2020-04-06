package com.setu.billsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.setu.billsystem.model.CustomerAccount;
import com.setu.billsystem.service.CustomerServiceImpl;

@RestController
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	@PostMapping("/customer")
	public @ResponseBody CustomerAccount addCustomer(@RequestBody CustomerAccount customerAccount) {
		
		return customerServiceImpl.addCustomerAccount(customerAccount);
		
	}
	
	@GetMapping("/customer")
	public @ResponseBody List<CustomerAccount> getAll(){
		
		return customerServiceImpl.getAllCustomers();
	}
	
}
