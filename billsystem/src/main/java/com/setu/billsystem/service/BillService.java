package com.setu.billsystem.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.setu.billsystem.model.Bill;
import com.setu.billsystem.model.CustomerIdentifier;
import com.setu.billsystem.requestresponse.bill.FetchBillRequest;
import com.setu.billsystem.requestresponse.bill.FetchBillResponse;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptRequest;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptResponse;


public interface BillService {
	
	public List<Bill> getCustomerBill(List<CustomerIdentifier> customerIdentifiers);
	
	public FetchBillResponse fetchBillResponse(FetchBillRequest billRequest);
	
	public Bill addBill(Bill bill);
	
	public FetchReceiptResponse fetchReceiptResponse(FetchReceiptRequest fetchReceiptRequest);
	
}
