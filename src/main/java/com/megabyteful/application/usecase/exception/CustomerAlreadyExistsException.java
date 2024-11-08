package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class CustomerAlreadyExistsException extends BusinessException {

  private static final String ERROR_CODE = "already_exists";
  private static final String MESSAGE = "Customer [%s] with CPF [%s] already exists.";

  public CustomerAlreadyExistsException(final String name, final String cpf) {
    super(format(MESSAGE, name, cpf), ERROR_CODE);
  }
}
