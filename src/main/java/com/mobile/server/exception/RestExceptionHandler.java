package com.mobile.server.exception;

import com.mobile.server.exception.types.ApiExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status, final WebRequest request) {
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " : " + error.getDefaultMessage());
        }
        for (final ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid data provided", errors);

        return handleExceptionInternal(exception, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ApiExceptions.ConnectionException.class, ApiExceptions.LogicException.class, ApiExceptions.ParameterException.class, ApiExceptions.InvalidBusinessArgumentException.class})
    public final ResponseEntity<Object> handleException(
            RuntimeException exception) {
        final String error = "Status Code: " + HttpStatus.NOT_ACCEPTABLE.value() + ", Exception: " + exception.getClass().getSimpleName();

        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_ACCEPTABLE, exception.getLocalizedMessage(), error)
                , new HttpHeaders()
                , HttpStatus.NOT_ACCEPTABLE);
    }

}
