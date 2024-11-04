package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class ServiceProviderNotFoundException extends BusinessException {

  private static final String ERROR_CODE = "not_found";
  private static final String MESSAGE = "Service provider with id [%s] not found.";

  public ServiceProviderNotFoundException(final int id) {
    super(format(MESSAGE, id), ERROR_CODE);
  }
}
