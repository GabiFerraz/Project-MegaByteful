package com.megabyteful.infrastructure.api.fixture;

import com.megabyteful.application.domain.Appointment;
import java.time.LocalDateTime;

public class AppointmentControllerTestFixture {

  public static Appointment validAppointmentRequest() {
    return Appointment.builder()
        .scheduleId(1)
        .customerId(1)
        .serviceTime(LocalDateTime.parse("2024-11-10T15:00:00"))
        .build();
  }

  public static Appointment validAppointmentResponse() {
    return Appointment.builder()
        .id(999)
        .scheduleId(999)
        .customerId(999)
        .serviceTime(LocalDateTime.parse("2024-11-10T15:00:00"))
        .build();
  }
}
