package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

public class AppointmentNotFoundException extends BusinessException {

  private static final String ERROR_CODE = "not_found";
  private static final String MESSAGE = "Appointment with Id [%s] not found.";

  public AppointmentNotFoundException(final int id) {
    super(format(MESSAGE, id), ERROR_CODE);
  }
}
