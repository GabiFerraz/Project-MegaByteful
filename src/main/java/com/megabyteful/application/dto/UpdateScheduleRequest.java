package com.megabyteful.application.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateScheduleRequest {

  @Future(message = "Date must be in the future")
  @NotNull(message = "Date cannot be null")
  private LocalDateTime serviceTime;

  @NotEmpty(message = "Available times cannot be empty")
  private List<String> availableTimes;
}
