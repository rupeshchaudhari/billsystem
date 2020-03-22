
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class Total {

	public Total(String displayName, Amount amount) {
		super();
		this.displayName = displayName;
		this.amount = amount;
	}

    private String displayName;
	
    private Amount amount;

}