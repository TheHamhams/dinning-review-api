package com.dining.reviewapi.exceptions;

public class AdminIdNotFoundException extends RuntimeException{
    public AdminIdNotFoundException(String message) {
        super(message);
    }
}
