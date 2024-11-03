package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class AppointmentAlreadyExistsException extends BusinessException {

  private static final String ERROR_CODE = "already_exists";
  private static final String MESSAGE = "Appointment with schedule id [%s] already exists.";

  public AppointmentAlreadyExistsException(final Integer id) {
    super(format(MESSAGE, id), ERROR_CODE);
  }
}
