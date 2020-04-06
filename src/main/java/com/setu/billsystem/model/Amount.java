
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class Amount {
	
	public Amount(Double value) {
		super();
		this.value = value;
	}

	private Double value;

}
