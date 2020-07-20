package com.lease.rate.exception;

public class LeaseRateCalculatorException extends RuntimeException {
    public LeaseRateCalculatorException(String message) {
        super(message);
    }
    public LeaseRateCalculatorException(String message, Throwable  cause) {
        super(message, cause);
    }

}
