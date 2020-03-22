
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class Bill {
	
	public Bill(String billerBillID, String generatedOn, String recurrence, String amountExactness,
			CustomerAccount customerAccount, Aggregates aggregates) {
		super();
		this.billerBillID = billerBillID;
		this.generatedOn = generatedOn;
		this.recurrence = recurrence;
		this.amountExactness = amountExactness;
		this.customerAccount = customerAccount;
		this.aggregates = aggregates;
	}


    private String billerBillID;
    private String generatedOn;
    private String recurrence;
    private String amountExactness;
    private CustomerAccount customerAccount;

    private Aggregates aggregates;

}
