package com.mobile.server.exception;

public class ApiExceptions {
    public class InvalidBusinessArgumentException extends RuntimeException{
        public InvalidBusinessArgumentException(String message) {
            super(message);
        }
    }

    public static class LogicException extends RuntimeException {
        public LogicException(String message) {
            super(message);
        }
    }

    public static class ParameterException extends RuntimeException{
        public ParameterException(String message) {
            super(message);
        }
    }
}
