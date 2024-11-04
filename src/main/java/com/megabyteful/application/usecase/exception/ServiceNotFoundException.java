package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class ServiceNotFoundException extends BusinessException {

  private static final String ERROR_CODE = "not_found";
  private static final String MESSAGE = "Service with id [%s] not found.";

  public ServiceNotFoundException(final int id) {
    super(format(MESSAGE, id), ERROR_CODE);
  }
}
