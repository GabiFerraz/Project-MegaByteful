package com.megabyteful.application.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {
  private int id;

  @NotNull(message = "Schedule ID is required")
  private int scheduleId;

  @NotNull(message = "Customer ID is required")
  private int customerId;

  @NotNull(message = "Service Provider ID is required")
  private int serviceProviderId;

  public static Appointment createAppointment(
          final int id, int scheduleId, int customerId, final int serviceProviderID) {

    return new Appointment(id, scheduleId, customerId, serviceProviderID);
  }
}
