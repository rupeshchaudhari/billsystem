
package com.setu.billsystem.model;

import java.util.List;

import lombok.Data;

@Data
public class BillDetails {

    private String billFetchStatus;
    private List<Bill> bills = null;

}
