package com.car.data.api.exception;

public class CarDataNotFoundException extends RuntimeException{
    public CarDataNotFoundException(String message) {
        super(message);
    }
}
