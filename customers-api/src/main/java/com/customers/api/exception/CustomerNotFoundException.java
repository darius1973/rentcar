package com.customers.api.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
    public CustomerNotFoundException(Exception exception) {super(exception);}
}
