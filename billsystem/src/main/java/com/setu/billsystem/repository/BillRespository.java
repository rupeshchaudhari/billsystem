package com.setu.billsystem.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.setu.billsystem.model.Aggregates;
import com.setu.billsystem.model.Amount;
import com.setu.billsystem.model.Bill;
import com.setu.billsystem.model.CustomerAccount;
import com.setu.billsystem.model.Total;

@Component
public class BillRespository{

	List<Bill> bills = new ArrayList<Bill>();
	
	
	public BillRespository() {
		super();
		bills.add(new Bill("12123131322", "2019-08-01T08:28:12Z", "ONE_TIME", "EXACT", new CustomerAccount("8208021440", "Rupesh"), new Aggregates(new Total("Total Outstanding", new Amount(99000.00)))));
		bills.add(new Bill("12123131123", "2020-03-14T08:28:12Z", "MONTHLY", "EXACT", new CustomerAccount("8481779999", "Ashok"), new Aggregates(new Total("Total Outstanding", new Amount(75999.50)))));
		
	}

	public List<Bill> findByCustomerAccount(CustomerAccount customerAccount) {
		
		List<Bill> billsOfCustomer = new ArrayList<Bill>();
		
		for (Bill bill : bills) {
			if(bill.getCustomerAccount().getId().contentEquals(customerAccount.getId()))
				billsOfCustomer.add(bill);
		}
		return billsOfCustomer;
	}
	
	public Bill save(Bill bill) {
		bills.add(bill);	
		return bill;	
	}
	
	public Bill findById(String billerBillID) {
		for (Bill bill : bills) {
			if(bill.getBillerBillID().contentEquals(billerBillID))
				return bill;
		}
		return null;
	}
	
}
