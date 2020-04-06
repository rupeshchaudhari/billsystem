package com.setu.billsystem.requestresponse.bill;

import java.util.List;

import com.setu.billsystem.model.CustomerIdentifier;

import lombok.Data;

@Data
public class FetchBillRequest {

	private List<CustomerIdentifier> customerIdentifiers = null;
	
}
