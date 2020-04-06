package com.setu.billsystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setu.billsystem.model.Bill;
import com.setu.billsystem.model.BillDetails;
import com.setu.billsystem.model.CustomerAccount;
import com.setu.billsystem.model.CustomerIdentifier;
import com.setu.billsystem.model.Data;
import com.setu.billsystem.model.Error;
import com.setu.billsystem.repository.BillRespository;
import com.setu.billsystem.repository.CustomerAccountRepository;
import com.setu.billsystem.requestresponse.bill.FetchBillRequest;
import com.setu.billsystem.requestresponse.bill.FetchBillResponse;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptRequest;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptResponse;
import com.setu.billsystem.requestresponse.receipt.Receipt;

@Service
public class BillServiceImpl implements BillService {

	
	@Autowired
	BillRespository billRepository;
	
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	
	@Override
	public List<Bill> getCustomerBill(CustomerIdentifier customerIdentifier) {
		
		List<Bill> bills=null;
		
			if(customerIdentifier.getAttributeName()!=null && customerIdentifier.getAttributeValue()!=null)
			{
				if(customerIdentifier.getAttributeName().equalsIgnoreCase("mobileNumber")) {
					CustomerAccount customerAccount = customerAccountRepository.findById(customerIdentifier.getAttributeValue());
					if(customerAccount!=null) {
						bills = billRepository.findByCustomerAccount(customerAccount);
					}
					
				}
			}
		
		return bills;
		
	}

	@Override
	public FetchBillResponse fetchBillResponse(FetchBillRequest billRequest) {
		FetchBillResponse fetchBillResponse = new FetchBillResponse();
		
		List<CustomerIdentifier> customerIdentifiers = billRequest.getCustomerIdentifiers();
		for (CustomerIdentifier customerIdentifier : customerIdentifiers) {
			if(customerIdentifier.getAttributeName()!=null && customerIdentifier.getAttributeValue()!=null)
			{
				CustomerAccount customerAccount = customerAccountRepository.findById(customerIdentifier.getAttributeValue());
				if(customerAccount!=null) {
					List<Bill> bills = getCustomerBill(customerIdentifier);
					Data data = new Data();
					data.setCustomerAccount(customerAccount);
					BillDetails billDetails = new BillDetails();
					data.setBillDetails(billDetails);
					billDetails.setBills(bills);
					if(bills.size()==0) {
						billDetails.setBillFetchStatus("NO_OUTSTANDING");	
					}else {
						billDetails.setBillFetchStatus("AVAILABLE");	
					}
					fetchBillResponse.setData(data);
					fetchBillResponse.setStatus(200);
					fetchBillResponse.setSuccess(true);
					
				}else {
					Error error = setCustomerError();
					fetchBillResponse.setStatus(404);
					fetchBillResponse.setSuccess(false);
					fetchBillResponse.setError(error);
				}
			}else {
				Error error = setCustomerError();
				fetchBillResponse.setStatus(404);
				fetchBillResponse.setSuccess(false);
				fetchBillResponse.setError(error);
			}
		}
		
		
		
		return fetchBillResponse;
	}

	private Error setCustomerError() {
		Error error = new Error();
		error.setCode("customer-not-found");
		error.setTitle("Customer not found");
		error.setTraceID("");
		error.setDescription("The requested customer was not found in the biller system.");
		error.setParam("");
		error.setDocURL("");
		return error;
	}

	@Override
	public Bill addBill(Bill bill) {
		
		bill = billRepository.save(bill);
		
		return bill;
	}

	
	
	@Override
	public FetchReceiptResponse fetchReceiptResponse(FetchReceiptRequest fetchReceiptRequest) {
		
		FetchReceiptResponse fetchReceiptResponse = new FetchReceiptResponse();
		Double paid = fetchReceiptRequest.getPaymentDetails().getAmountPaid().getValue();
		
		Bill bill = billRepository.findById(fetchReceiptRequest.getBillerBillID());
		if(bill!=null) {
			Double outstanding = bill.getAggregates().getTotal().getAmount().getValue();
			Double remaining = outstanding - paid;
			if(remaining<=0) {
				billRepository.delete(bill);
			}else {
				bill.getAggregates().getTotal().getAmount().setValue(outstanding-paid);
			}
			fetchReceiptResponse.setStatus(200);
			fetchReceiptResponse.setSuccess(true);
			com.setu.billsystem.requestresponse.receipt.Data data = new com.setu.billsystem.requestresponse.receipt.Data();
			data.setBillerBillID(fetchReceiptRequest.getBillerBillID());
			data.setPlatformBillID(fetchReceiptRequest.getPlatformBillID());
			data.setPlatformTransactionRefID(fetchReceiptRequest.getPaymentDetails().getPlatformTransactionRefID());
			Receipt receipt = new Receipt();
			String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
			String dateString = simpleDateFormat.format(new Date());
			receipt.setDate(dateString);
			receipt.setId("R"+fetchReceiptRequest.getBillerBillID());
			data.setReceipt(receipt);
			fetchReceiptResponse.setData(data);
		}else {
			fetchReceiptResponse.setStatus(404);
			fetchReceiptResponse.setSuccess(false);
			com.setu.billsystem.requestresponse.receipt.Data data = new com.setu.billsystem.requestresponse.receipt.Data();
			Receipt receipt = new Receipt();
			receipt.setDate(new Data().toString());
			receipt.setId("R"+fetchReceiptRequest.getBillerBillID());
			data.setReceipt(receipt);
			fetchReceiptResponse.setData(data);
		}
		
		return fetchReceiptResponse;
	}

	@Override
	public void initialize() {
		billRepository.initialize();
		
	}


}
