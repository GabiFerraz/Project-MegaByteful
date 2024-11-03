package com.megabyteful.application.usecase.exception;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public class FeedbackNotFoundException extends RuntimeException {

  private static final String ERROR_CODE = "feedback_not_found";
  private static final String MESSAGE = "Feedback for professional with ID [%s] not found.";

  public FeedbackNotFoundException(final int professionalId) {
    super(format(MESSAGE, professionalId));
  }
}
