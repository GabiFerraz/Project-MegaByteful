package com.megabyteful.application.usecase.fixture;

import com.megabyteful.application.domain.Appointment;
import java.time.LocalDateTime;

public class AppointmentTestFixture {

  public static Appointment validAppointment() {
    return Appointment.builder()
        .customerId(1)
        .scheduleId(1)
        .serviceTime(LocalDateTime.parse("2022-01-01T00:00:00"))
        .build();
  }

  public static Appointment appointmentToUpdate() {
    return Appointment.builder()
        .customerId(1)
        .scheduleId(1)
        .serviceTime(LocalDateTime.parse("2022-01-01T00:00:00"))
        .build();
  }

  public static Appointment validAppointmentResponse() {
    return Appointment.builder()
        .id(1)
        .customerId(1)
        .scheduleId(1)
        .serviceTime(LocalDateTime.parse("2022-01-01T00:00:00"))
        .build();
  }
}
