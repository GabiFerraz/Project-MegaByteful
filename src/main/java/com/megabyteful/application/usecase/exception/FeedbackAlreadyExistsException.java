package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class FeedbackAlreadyExistsException extends BusinessException {

  private static final String ERROR_CODE = "feedback_already_exists";
  private static final String MESSAGE =
      "Feedback for professional [%s] and service [%s] already exists.";

  public FeedbackAlreadyExistsException(final int professionalId, final int serviceId) {
    super(format(MESSAGE, professionalId, serviceId), ERROR_CODE);
  }
}
