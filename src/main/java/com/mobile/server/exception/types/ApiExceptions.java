package com.mobile.server.exception.types;

public class ApiExceptions {
    public static class InvalidBusinessArgumentException extends RuntimeException{
        public InvalidBusinessArgumentException(String message) {
            super(message);
        }
    }

    public static class InvalidLoginDetails extends RuntimeException{
        public InvalidLoginDetails(String message) {
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

    public static class TokenException extends RuntimeException{
        public TokenException(String message) {
            super(message);
        }
    }

    public static class ConnectionException extends RuntimeException{
        public ConnectionException(String message) {
            super(message);
        }
    }
}
