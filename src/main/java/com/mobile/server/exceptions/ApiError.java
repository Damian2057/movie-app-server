package com.mobile.server.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.httpStatus = status;
        this.message = message;
        errors = List.of(error);
    }
}