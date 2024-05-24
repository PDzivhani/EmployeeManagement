package com.teamc.ems.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

// responsible for handling exceptions involved with rest api
@RestControllerAdvice
public class ControllerExceptionHandler {

    // special sticker to tell this class that it handle this specific exception, creates a map
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    //special sticker to tell this class which response code to answer with
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CustomErrorResponse resourceNotFoundException(@NonNull ResourceNotFoundException resourceNotFoundException,@NonNull WebRequest webRequest){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false)
        );

        return customErrorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomErrorResponse globalExceptionHandler(@NonNull  Exception exception,@NonNull WebRequest webRequest){
        CustomErrorResponse message = new CustomErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return message;
    }

}
