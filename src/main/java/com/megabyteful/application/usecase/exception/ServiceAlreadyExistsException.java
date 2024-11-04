package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class ServiceAlreadyExistsException extends BusinessException {
  private static final String ERROR_CODE = "already_exists";
  private static final String MESSAGE = "Service with id [%s] already exists.";

  public ServiceAlreadyExistsException(final int id) {
    super(format(MESSAGE, id), ERROR_CODE);
  }
}
