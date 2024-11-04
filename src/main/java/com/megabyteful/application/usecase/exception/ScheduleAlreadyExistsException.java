package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ScheduleAlreadyExistsException extends BusinessException {

  private static final String ERROR_CODE = "already_exists";
  private static final String MESSAGE = "Schedule for time [%s] already exists.";

  public ScheduleAlreadyExistsException(final LocalDateTime seviceTime) {
    super(format(MESSAGE, seviceTime), ERROR_CODE);
  }
}
