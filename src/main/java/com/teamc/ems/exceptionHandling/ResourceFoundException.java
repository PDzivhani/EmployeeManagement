package com.teamc.ems.exceptionHandling;

public class ResourceFoundException extends RuntimeException{
    public ResourceFoundException(String message){
        super(message);
    }
}
