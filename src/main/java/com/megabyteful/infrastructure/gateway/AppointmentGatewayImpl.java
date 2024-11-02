package com.megabyteful.infrastructure.gateway;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import com.megabyteful.infrastructure.persistence.repository.AppointmentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AppointmentGatewayImpl implements AppointmentGateway {

  private final AppointmentRepository appointmentRepository;

  @Override
  public Appointment save(final Appointment appointment) {
    final var entity =
        AppointmentEntity.builder()
            .id(appointment.getId())
            .customerId(appointment.getCustomerId())
            .scheduleId(appointment.getScheduleId())
            .serviceProviderID(appointment.getServiceProviderID())
            .build();

    final var saved = appointmentRepository.save(entity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Appointment> findById(int id) {
    return appointmentRepository.findById(id).map(this::toResponse);
  }

  @Override
  public Appointment updateAppointment(Appointment appointment) {
    final var appointmentFound =
        appointmentRepository
            .findById(appointment.getId())
            .orElseThrow(() -> new RuntimeException(""));

    final var newEntity =
        AppointmentEntity.builder()
            .id(appointmentFound.getId())
            .scheduleId(appointment.getScheduleId())
            .customerId(appointment.getCustomerId())
            .serviceProviderID(appointment.getServiceProviderID())
            .build();

    final var updated = appointmentRepository.save(newEntity);

    return this.toResponse(updated);
  }

  @Transactional
  @Override
  public void delete(final int id) {
    appointmentRepository.deleteById(id);
  }

  private Appointment toResponse(final AppointmentEntity entity) {
    return new Appointment(
        entity.getId(),
        entity.getScheduleId(),
        entity.getCustomerId(),
        entity.getServiceProviderID());
  }
}