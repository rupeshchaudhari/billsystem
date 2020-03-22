package com.setu.billsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setu.billsystem.model.CustomerAccount;
import com.setu.billsystem.repository.CustomerAccountRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerAccountRepository customerAccountRepository;
	
	@Override
	public CustomerAccount addCustomerAccount(CustomerAccount customerAccount) {
		customerAccount = customerAccountRepository.save(customerAccount);
		return customerAccount;
	}
	
	public List<CustomerAccount> getAllCustomers(){
		return customerAccountRepository.findAll();
	}
	

}
