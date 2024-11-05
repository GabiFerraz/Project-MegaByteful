package com.megabyteful.infrastructure.gateway;

import static java.lang.String.format;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.gateway.ScheduleGateway;
import com.megabyteful.application.usecase.exception.ScheduleNotFoundException;
import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import com.megabyteful.infrastructure.persistence.entity.ScheduleEntity;
import com.megabyteful.infrastructure.persistence.entity.ServiceEntity;
import com.megabyteful.infrastructure.persistence.repository.ScheduleRepository;
import com.megabyteful.infrastructure.persistence.repository.ServiceRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ScheduleGatewayImpl implements ScheduleGateway {

  private final ScheduleRepository scheduleRepository;
  private final ServiceRepository serviceRepository;

  @Override
  public Schedule save(final Schedule schedule) {
    final Optional<ServiceEntity> serviceEntityFound =
        serviceRepository.findById(schedule.getServiceId());

    if (serviceEntityFound.isEmpty()) {
      throw new IllegalArgumentException(
          format("Service with id [%s] not found", schedule.getServiceId()));
    }

    final var serviceEntity = serviceEntityFound.get();

    final var scheduleEntity =
        ScheduleEntity.builder()
            .service(serviceEntity)
            .appointments(List.of())
            .serviceTime(schedule.getServiceTime())
            .availableTimes(schedule.getAvailableTimes())
            .build();

    final var saved = scheduleRepository.save(scheduleEntity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Schedule> findByServiceTime(final LocalDateTime serviceTime) {
    return scheduleRepository.findByServiceTime(serviceTime).map(this::toResponse);
  }

  @Override
  public Schedule update(final Schedule schedule) {
    final var scheduleFound =
        scheduleRepository
            .findByServiceTime(schedule.getServiceTime())
            .orElseThrow(() -> new ScheduleNotFoundException(schedule.getServiceTime()));

    final var scheduleEntity =
        ScheduleEntity.builder()
            .id(scheduleFound.getId())
            .service(scheduleFound.getService())
            .appointments(scheduleFound.getAppointments())
            .serviceTime(schedule.getServiceTime())
            .availableTimes(schedule.getAvailableTimes())
            .build();

    final var updated = scheduleRepository.save(scheduleEntity);

    return this.toResponse(updated);
  }

  @Transactional
  @Override
  public void delete(final LocalDateTime serviceTime) {
    scheduleRepository.deleteByServiceTime(serviceTime);
  }

  private Schedule toResponse(final ScheduleEntity entity) {
    return new Schedule(
        entity.getId(),
        entity.getService().getId(),
        this.toAppointmentDomain(entity.getAppointments()),
        entity.getServiceTime(),
        entity.getAvailableTimes());
  }

  private List<Appointment> toAppointmentDomain(final List<AppointmentEntity> appointmentEntities) {
    return appointmentEntities.stream()
        .map(
            appointmentEntity ->
                new Appointment(
                    appointmentEntity.getId(),
                    appointmentEntity.getSchedule().getId(),
                    appointmentEntity.getCustomer().getId(),
                    appointmentEntity.getServiceTime()))
        .toList();
  }
}
