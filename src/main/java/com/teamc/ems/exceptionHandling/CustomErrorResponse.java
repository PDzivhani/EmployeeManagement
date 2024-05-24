package com.teamc.ems.exceptionHandling;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CustomErrorResponse {

    private final int httpStatus;
    private final String message;
    private final String description;
    private final String timeStamp;


    public CustomErrorResponse(int httpStatus, String message, String description) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.description = description;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
