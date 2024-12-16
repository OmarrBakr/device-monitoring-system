package com.example.task3.exceptions;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException(String message) {
        super(message);
    }
}