package com.megabyteful.application.usecase.exception;

import lombok.Getter;

@Getter
public class ServiceAlreadyExistsException extends BusinessException {
	private static final String ERROR_CODE = "ERROR_S001";
	private static final String MESSAGE = "Service already_exists";

  public ServiceAlreadyExistsException() {
    super(MESSAGE, ERROR_CODE);
  }
}
