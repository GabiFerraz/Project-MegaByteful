package com.megabyteful.infrastructure.api.fixture;

import com.megabyteful.application.domain.Appointment;
import java.time.LocalDateTime;

public class AppointmentControllerTestFixture {

  public static Appointment validAppointmentRequest() {
    return Appointment.builder()
        .id(999)
        .scheduleId(999)
        .customerId(999)
        .serviceTime(LocalDateTime.parse("2022-01-01T00:00:00"))
        .build();
  }

  public static Appointment validAppointmentResponse() {
    return Appointment.builder()
        .id(999)
        .scheduleId(999)
        .customerId(999)
        .serviceTime(LocalDateTime.parse("2022-01-01T00:00:00"))
        .build();
  }
}
