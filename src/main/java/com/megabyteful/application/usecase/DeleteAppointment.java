package com.megabyteful.application.usecase;

import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAppointment {

  private final AppointmentGateway gateway;

  public void execute(final int id) {
    final var appointment =
        gateway.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));

    gateway.delete(appointment.getId());
  }
}
