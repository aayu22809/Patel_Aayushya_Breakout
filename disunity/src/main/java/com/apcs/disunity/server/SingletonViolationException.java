package com.apcs.disunity.server;

public class SingletonViolationException extends RuntimeException {
    public SingletonViolationException(String message) {
        super(message);
    }
}
