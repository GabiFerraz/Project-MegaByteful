package com.megabyteful.application.usecase.exception;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public class CustomerAlreadyExistsException extends BusinessException {

    private static final String ERROR_CODE = "already_exists";
    private static final String MESSAGE = "Customer %s with cpf %s already exists.";

    public CustomerAlreadyExistsException(final String name, final String cpf) {
        super(format(MESSAGE, name, cpf), ERROR_CODE);
    }
}
