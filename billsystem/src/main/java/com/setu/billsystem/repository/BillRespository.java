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
		bills.add(new Bill("12123131322", "2020-04-02T19:49:00Z", "ONE_TIME", "EXACT", new CustomerAccount("8208021440", "Rupesh"), new Aggregates(new Total("Total Outstanding", new Amount(99000.00)))));
		bills.add(new Bill("12123131123", "2020-04-10T19:20:00Z", "MONTHLY", "EXACT", new CustomerAccount("8481779999", "Ashok"), new Aggregates(new Total("Total Outstanding", new Amount(75999.50)))));
		bills.add(new Bill("12123131124", "2020-04-13T19:49:00Z", "YEARLY", "EXACT", new CustomerAccount("8481778080", "Puneet"), new Aggregates(new Total("Total Outstanding", new Amount(80999.50)))));
		bills.add(new Bill("12123131125", "2020-04-13T19:49:00Z", "ONE_TIME", "EXACT", new CustomerAccount("8481778080", "Puneet"), new Aggregates(new Total("Total Outstanding", new Amount(73.23)))));
		bills.add(new Bill("12123131126", "2020-04-13T19:49:00Z", "MONTHLY", "EXACT", new CustomerAccount("8481778080", "Puneet"), new Aggregates(new Total("Total Outstanding", new Amount(102123.23)))));

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
	
	public void initialize() {
		bills.clear();
		bills.add(new Bill("12123131322", "2020-04-02T19:49:00Z", "ONE_TIME", "EXACT", new CustomerAccount("8208021440", "Rupesh"), new Aggregates(new Total("Total Outstanding", new Amount(99000.00)))));
		bills.add(new Bill("12123131123", "2020-04-10T19:20:00Z", "MONTHLY", "EXACT", new CustomerAccount("8481779999", "Ashok"), new Aggregates(new Total("Total Outstanding", new Amount(75999.50)))));
		bills.add(new Bill("12123131124", "2020-04-13T19:49:00Z", "YEARLY", "EXACT", new CustomerAccount("8481778080", "Puneet"), new Aggregates(new Total("Total Outstanding", new Amount(80999.50)))));
		bills.add(new Bill("12123131125", "2020-04-13T19:49:00Z", "ONE_TIME", "EXACT", new CustomerAccount("8481778080", "Puneet"), new Aggregates(new Total("Total Outstanding", new Amount(73.23)))));
		bills.add(new Bill("12123131126", "2020-04-13T19:49:00Z", "MONTHLY", "EXACT", new CustomerAccount("8481778080", "Puneet"), new Aggregates(new Total("Total Outstanding", new Amount(102123.23)))));
	}
	
}
