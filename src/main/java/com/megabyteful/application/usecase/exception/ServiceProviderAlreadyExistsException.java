package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

public class ServiceProviderAlreadyExistsException extends BusinessException {

	  private static final String ERROR_CODE = "already_exists";
	  private static final String MESSAGE = "Service provider [%s] with document [%s] already exists.";

	  public ServiceProviderAlreadyExistsException(String name, String document) {
	    super(format(MESSAGE, name, document), ERROR_CODE);
	  }
}
