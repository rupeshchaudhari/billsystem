package com.setu.billsystem.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.setu.billsystem.model.CustomerAccount;


@Component
public class CustomerAccountRepository{
	
	List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
	
	
	public CustomerAccountRepository() {
		super();
		customerAccounts.add(new CustomerAccount("8208021440","Rupesh"));
		customerAccounts.add(new CustomerAccount("8481779999","Ashok"));
	}

	public CustomerAccount findById(String id) {
		
		for (CustomerAccount customerAccount : customerAccounts) {
			if(customerAccount.getId().contentEquals(id)) {
				return customerAccount;
			}
		}
		return null;
	}
	
	public CustomerAccount save(CustomerAccount customerAccount) {
		
		customerAccounts.add(customerAccount);
		return customerAccount;
	}
	
	public List<CustomerAccount> findAll(){
		return customerAccounts;
	}
	
}
