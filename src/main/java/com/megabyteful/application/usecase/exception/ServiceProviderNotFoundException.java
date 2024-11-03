package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

public class ServiceProviderNotFoundException extends BusinessException {

	private static final String ERROR_CODE = "not_found";
	private static final String MESSAGE = "Service Provider [%s] with document [%s] not found.";

	public ServiceProviderNotFoundException(String document) {
		super(format(MESSAGE, document), ERROR_CODE);
	}
}
