package com.megabyteful.application.domain;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {
  private Integer id;

  @NotNull(message = "Schedule ID is required")
  private Integer scheduleId;

  @NotNull(message = "Customer ID is required")
  private Integer customerId;

  @Future(message = "Date must be in the future")
  @NotNull(message = "Date is required")
  private LocalDateTime serviceTime;

  public static Appointment createAppointment(
      final Integer scheduleId, final Integer customerId, final LocalDateTime serviceTime) {

    return new Appointment(null, scheduleId, customerId, serviceTime);
  }
}
