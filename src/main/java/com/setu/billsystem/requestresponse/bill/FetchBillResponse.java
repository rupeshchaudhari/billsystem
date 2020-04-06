package com.setu.billsystem.requestresponse.bill;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.setu.billsystem.model.Data;
import com.setu.billsystem.model.Error;

@lombok.Data
@JsonInclude(Include.NON_NULL)
public class FetchBillResponse {
    private Integer status;
    private Boolean success;
    private Data data;
    private Error error;
}
