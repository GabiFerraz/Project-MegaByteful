package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAppointment {
  private final AppointmentGateway gateway;

  public Appointment execute(final int id, final Appointment request) {
    final var appointmentFound =
        gateway.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));

    appointmentFound.setServiceTime(request.getServiceTime());

    return gateway.update(appointmentFound);
  }
}
