
package com.setu.billsystem.requestresponse.receipt;

import com.setu.billsystem.model.PaymentDetails;

import lombok.Data;

@Data
public class FetchReceiptRequest {

    private String billerBillID;
    private String platformBillID;
    private PaymentDetails paymentDetails;

}
