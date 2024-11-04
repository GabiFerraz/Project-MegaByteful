package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ScheduleNotFoundException extends BusinessException {

  private static final String ERROR_CODE = "not_found";
  private static final String MESSAGE = "Schedule for time [%s] not found.";

  public ScheduleNotFoundException(final LocalDateTime serviceTime) {
    super(format(MESSAGE, serviceTime), ERROR_CODE);
  }
}
