package com.dining.reviewapi.exceptions;

public class InvalidApprovalStatusException extends RuntimeException {
    public InvalidApprovalStatusException(String message) {
        super(message);
    }
}
