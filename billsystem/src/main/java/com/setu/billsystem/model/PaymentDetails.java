
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class PaymentDetails {

    private String platformTransactionRefID;
    private String uniquePaymentRefID;
    private AmountPaid amountPaid;
    private BillAmount billAmount;

}
