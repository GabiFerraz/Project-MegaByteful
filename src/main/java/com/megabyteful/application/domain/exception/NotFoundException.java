package com.megabyteful.application.domain.exception;

public class NotFoundException extends DomainException {

	public NotFoundException(final String message) {
		super(message);
	}
}