package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAppointment {

  private final AppointmentGateway gateway;

  public Appointment execute(final Appointment appointmentDomain) {

    final var appointment = gateway.findById(appointmentDomain.getId());

    if (appointment.isPresent()) {
      throw new AppointmentAlreadyExistsException(appointmentDomain.getId());
    }

    final var buildDomain =
        Appointment.createAppointment(
            appointmentDomain.getId(),
            appointmentDomain.getScheduleId(),
            appointmentDomain.getCustomerId(),
            appointmentDomain.getServiceProviderID());

    return gateway.save(buildDomain);
  }
}
