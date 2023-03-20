package com.dining.reviewapi.exceptions;

public class AdminNameNotFoundException extends RuntimeException{
    public AdminNameNotFoundException(String message) {
        super(message);
    }
}
