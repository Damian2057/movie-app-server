package com.mobile.server.exceptions.types;

public class InvalidBusinessArgumentException extends RuntimeException{
    public InvalidBusinessArgumentException(String message) {
        super(message);
    }
}
