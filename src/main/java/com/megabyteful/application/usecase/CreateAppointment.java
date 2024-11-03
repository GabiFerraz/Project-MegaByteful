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

  public Appointment execute(final Appointment request, final String cpf) {

    final var appointmentExist =
        gateway.existsByScheduleCustomerAndTime(
            request.getScheduleId(), request.getCustomerId(), request.getServiceTime());

    if (appointmentExist) {
      throw new AppointmentAlreadyExistsException(request.getId());
    }

    final var buildDomain =
        Appointment.createAppointment(
            request.getScheduleId(), request.getCustomerId(), request.getServiceTime());

    return gateway.save(buildDomain, cpf);
  }
}
