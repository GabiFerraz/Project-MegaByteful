package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAppointment {

  private final AppointmentGateway gateway;

  public Appointment execute(final Appointment AppointmentDomain) {

    final var appointment = gateway.findById(AppointmentDomain.getId());

    if (appointment.isPresent()) {
      throw new RuntimeException("appointment already exists");
    }

    final var buildDomain = Appointment.createAppointment(
            AppointmentDomain.getId(),
            AppointmentDomain.getScheduleId(),
            AppointmentDomain.getCustomerId(),
            AppointmentDomain.getServiceProviderID()
        );

    return gateway.save(buildDomain);
  }
}
