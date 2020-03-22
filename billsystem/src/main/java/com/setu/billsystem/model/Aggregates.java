
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class Aggregates {

	public Aggregates(Total total) {
		super();
		this.total = total;
	}

    private Total total;

}
