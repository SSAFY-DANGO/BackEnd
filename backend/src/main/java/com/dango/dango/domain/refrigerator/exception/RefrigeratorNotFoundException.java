package com.dango.dango.domain.refrigerator.exception;

public class RefrigeratorNotFoundException extends RuntimeException {
    public RefrigeratorNotFoundException(String message) {
        super(message);
    }
}
