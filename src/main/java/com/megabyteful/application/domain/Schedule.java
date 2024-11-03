package com.megabyteful.application.domain;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

  private Integer id;

  @NotNull(message = "Service id cannot be null")
  private Integer serviceId;

  @Future(message = "Date must be in the future")
  @NotNull(message = "Date cannot be null")
  private LocalDateTime serviceTime;

  @NotEmpty(message = "Available times cannot be empty")
  private List<String> availableTimes;

  public static Schedule createSchedule(
      final Integer serviceId, final LocalDateTime serviceTime, final List<String> availableTimes) {
    return new Schedule(null, serviceId, serviceTime, availableTimes);
  }
}
