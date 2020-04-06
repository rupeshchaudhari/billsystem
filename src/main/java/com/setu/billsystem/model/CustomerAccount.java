
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class CustomerAccount {

	public CustomerAccount(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	private String id;

    private String name;

}
