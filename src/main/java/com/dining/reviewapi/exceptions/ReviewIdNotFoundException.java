package com.dining.reviewapi.exceptions;

public class ReviewIdNotFoundException extends RuntimeException {
    public ReviewIdNotFoundException(String message) {
        super(message);
    }
}
