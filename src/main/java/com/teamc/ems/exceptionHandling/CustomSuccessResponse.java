package com.teamc.ems.exceptionHandling;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CustomSuccessResponse {
    private final int httpStatus;
    private final String message;
    private final String description;
    private final String timeStamp;


    public CustomSuccessResponse(int httpStatus, String message, String description) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.description = description;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
