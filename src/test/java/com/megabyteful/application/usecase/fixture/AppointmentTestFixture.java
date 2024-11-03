package com.megabyteful.application.usecase.fixture;

import com.megabyteful.application.domain.Appointment;

public class AppointmentTestFixture {

  public static Appointment validAppointment() {
    return Appointment.builder().customerId(999).scheduleId(999).serviceProviderID(999).build();
  }

  public static Appointment appointmentToUpdate() {
    return Appointment.builder().customerId(999).scheduleId(999).serviceProviderID(999).build();
  }
}
