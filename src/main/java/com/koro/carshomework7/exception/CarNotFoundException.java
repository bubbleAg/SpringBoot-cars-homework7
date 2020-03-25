package com.koro.carshomework7.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(long id) {
        super("Could not find car with id: " + id);
    }
}
