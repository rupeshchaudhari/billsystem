
package com.setu.billsystem.requestresponse.receipt;

@lombok.Data
public class Data {

    private String billerBillID;
    private String platformBillID;
    private String platformTransactionRefID;
    private Receipt receipt;

}
