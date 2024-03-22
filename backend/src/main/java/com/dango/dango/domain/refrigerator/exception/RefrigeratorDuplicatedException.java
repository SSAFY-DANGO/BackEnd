package com.dango.dango.domain.refrigerator.exception;

public class RefrigeratorDuplicatedException extends RuntimeException {
    public RefrigeratorDuplicatedException(String message) {
        super(message);
    }
}
