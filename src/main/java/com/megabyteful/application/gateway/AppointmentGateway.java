package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Appointment;
import java.util.Optional;

public interface AppointmentGateway {

  Appointment save(final Appointment appointment, final String cpf);

  Optional<Appointment> findById(final int id);

  Appointment update(final Appointment appointment, final String cpf);

  void delete(final int id);
  ;
}
