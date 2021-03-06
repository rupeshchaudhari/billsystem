package com.setu.billsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.setu.billsystem.requestresponse.bill.FetchBillRequest;
import com.setu.billsystem.requestresponse.bill.FetchBillResponse;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptRequest;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptResponse;
import com.setu.billsystem.security.SetuJwtHelper;
import com.setu.billsystem.service.BillServiceImpl;

@RestController
public class BillsController {
	
	@Autowired
	BillServiceImpl billService;
	
	@Autowired
	SetuJwtHelper setuJwtHelper;
	
	@GetMapping("/bills/fetch")
	public @ResponseBody FetchBillResponse fetchBill(@RequestBody FetchBillRequest fetchBillRequest) {
		return billService.fetchBillResponse(fetchBillRequest);
	}
	
	@GetMapping("/bills/fetchReceipt")
	public @ResponseBody FetchReceiptResponse fetchBillReceipt(@RequestBody FetchReceiptRequest fetchReceiptRequest) {
		return billService.fetchReceiptResponse(fetchReceiptRequest);
		
	}
	
	@GetMapping("/bills/initialize")
	public String initialize() {
		billService.initialize();
		return "Done";
	}
	
//	@GetMapping("/token")
//	public String getToken(){
//		return setuJwtHelper.yieldBearerToken();
//	}
	
}
