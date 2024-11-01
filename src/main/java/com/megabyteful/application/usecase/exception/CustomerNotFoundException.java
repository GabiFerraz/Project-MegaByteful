package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

public class CustomerNotFoundException extends BusinessException {

  private static final String ERROR_CODE = "not_found";
  private static final String MESSAGE = "Customer with cpf %s not found.";

  public CustomerNotFoundException(final String cpf) {
    super(format(MESSAGE, cpf), ERROR_CODE);
  }
}
