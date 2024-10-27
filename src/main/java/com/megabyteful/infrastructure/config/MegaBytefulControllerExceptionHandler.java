package com.megabyteful.infrastructure.config;

import com.megabyteful.application.domain.exception.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MegaBytefulControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<Object> handleDomainException(final DomainException ex) {
        final var errorResponse = new ErrorResponse(ex.getMessage(), ex.getCause().toString());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
