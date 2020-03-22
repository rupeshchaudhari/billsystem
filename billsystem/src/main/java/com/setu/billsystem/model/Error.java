
package com.setu.billsystem.model;

import lombok.Data;

@Data
public class Error {

    private String code;
    private String title;
    private String traceID;
    private String description;
    private String param;
    private String docURL;

}
