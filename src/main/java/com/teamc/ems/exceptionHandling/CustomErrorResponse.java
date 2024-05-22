package com.teamc.ems.exceptionHandling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomErrorResponse {

    private String errorCode;
    private String message;
    private String timeStamp;

    public CustomErrorResponse(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
