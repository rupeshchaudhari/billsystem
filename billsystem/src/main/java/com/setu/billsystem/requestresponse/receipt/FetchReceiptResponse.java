package com.setu.billsystem.requestresponse.receipt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@lombok.Data
@JsonInclude(content = Include.NON_NULL)
public class FetchReceiptResponse {

    private Integer status;
    private Boolean success;
    private Data data;
	
}
