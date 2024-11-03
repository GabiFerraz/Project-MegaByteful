package com.megabyteful.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.megabyteful.application.domain.exception.DomainException;
import com.megabyteful.application.usecase.exception.BusinessException;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class MegaBytefulControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DomainException.class })
	public ResponseEntity<Object> handleDomainException(final DomainException ex) {
		log.error(ex.getMessage());
		final var errorResponse = new ErrorResponse(ex.getMessage(), ex.getCause().toString());

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<Object> handleBusinessException(final BusinessException ex) {
		log.error(ex.getMessage());
		final var errorResponse = new ErrorResponse(ex.getMessage(), ex.getErrorCode());

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler({ ServiceProviderNotFoundException.class })
	public ResponseEntity<Object> handleServiceProviderNotFoundException(final ServiceProviderNotFoundException ex) {
		log.error(ex.getMessage());
		final var errorResponse = new ErrorResponse(ex.getMessage(), ex.getErrorCode());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
}
