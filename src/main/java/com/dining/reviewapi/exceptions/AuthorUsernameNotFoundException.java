package com.dining.reviewapi.exceptions;

public class AuthorUsernameNotFoundException extends RuntimeException{
    public AuthorUsernameNotFoundException(String message) {
        super(message);
    }

}
