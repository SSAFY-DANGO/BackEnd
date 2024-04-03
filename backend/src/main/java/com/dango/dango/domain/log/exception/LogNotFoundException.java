package com.dango.dango.domain.log.exception;

public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException(String message) {
        super(message);
    }
}
