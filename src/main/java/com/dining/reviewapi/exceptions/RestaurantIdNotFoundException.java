package com.dining.reviewapi.exceptions;

public class RestaurantIdNotFoundException extends RuntimeException {
    public RestaurantIdNotFoundException(String message) {
        super(message);
    }
}
