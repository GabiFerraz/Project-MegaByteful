package com.megabyteful.infrastructure.config;

import com.megabyteful.application.domain.exception.DomainException;
import com.megabyteful.application.usecase.exception.BusinessException;
import com.megabyteful.application.usecase.exception.CustomerAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class MegaBytefulControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<Object> handleDomainException(final DomainException ex) {
        log.error(ex.getMessage());
        final var errorResponse = new ErrorResponse(ex.getMessage(), ex.getCause().toString());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(final BusinessException ex) {
        log.error(ex.getMessage());
        final var errorResponse = new ErrorResponse(ex.getMessage(), ex.getErrorCode());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", ex.getErrorCode());
        errorResponse.put("message", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
