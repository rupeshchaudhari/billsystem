package com.setu.billsystem.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.setu.billsystem.model.Bill;
import com.setu.billsystem.requestresponse.bill.FetchBillRequest;
import com.setu.billsystem.requestresponse.bill.FetchBillResponse;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptRequest;
import com.setu.billsystem.requestresponse.receipt.FetchReceiptResponse;
import com.setu.billsystem.security.SetuJwtHelper;
import com.setu.billsystem.service.BillService;
import com.setu.billsystem.service.BillServiceImpl;

@RestController
public class BillsController {
	
	@Autowired
	BillServiceImpl billService;
	
	@Autowired
	SetuJwtHelper setuJwtHelper;
	
	@GetMapping("/bills/fetch")
	public @ResponseBody FetchBillResponse fetchBill(@RequestBody FetchBillRequest fetchBillRequest,@RequestHeader("Authorization") String token) {
		System.out.println(token);
		return billService.fetchBillResponse(fetchBillRequest);
	}
	
	@GetMapping("/bills/fetchReceipt")
	public @ResponseBody FetchReceiptResponse fetchBillReceipt(@RequestBody FetchReceiptRequest fetchReceiptRequest) {
		return billService.fetchReceiptResponse(fetchReceiptRequest);
		
	}
	
	@GetMapping("/token")
	public String getToken(){
		return setuJwtHelper.yieldBearerToken();
	}
//	
//	@PostMapping("/bills/add")
//	public @ResponseBody Bill addBill(@RequestBody Bill bill ) {
//		return billService.addBill(bill);
//	}
	
}
