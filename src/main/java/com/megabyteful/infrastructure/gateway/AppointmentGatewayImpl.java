package com.megabyteful.infrastructure.gateway;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.gateway.AppointmentGateway;
import com.megabyteful.application.usecase.exception.AppointmentNotFoundException;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import com.megabyteful.application.usecase.exception.ScheduleNotFoundException;
import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import com.megabyteful.infrastructure.persistence.entity.CustomerEntity;
import com.megabyteful.infrastructure.persistence.entity.ScheduleEntity;
import com.megabyteful.infrastructure.persistence.repository.AppointmentRepository;
import com.megabyteful.infrastructure.persistence.repository.CustomerRepository;
import com.megabyteful.infrastructure.persistence.repository.ScheduleRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AppointmentGatewayImpl implements AppointmentGateway {

  private final AppointmentRepository appointmentRepository;
  private final CustomerRepository customerRepository;
  private final ScheduleRepository scheduleRepository;

  @Override
  public Appointment save(final Appointment request, final String cpf) {
    final Optional<ScheduleEntity> scheduleEntityFound =
        scheduleRepository.findByServiceTime(request.getServiceTime());

    if (scheduleEntityFound.isEmpty()) {
      throw new ScheduleNotFoundException(request.getServiceTime());
    }

    final Optional<CustomerEntity> customerEntityFound = customerRepository.findByCpf(cpf);

    if (customerEntityFound.isEmpty()) {
      throw new CustomerNotFoundException(cpf);
    }

    final var scheduleEntity = scheduleEntityFound.get();
    final var customerEntity = customerEntityFound.get();

    final var appointmentEntity =
        AppointmentEntity.builder()
            .schedule(scheduleEntity)
            .customer(customerEntity)
            .serviceTime(request.getServiceTime())
            .build();

    final var saved = appointmentRepository.save(appointmentEntity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Appointment> findById(int id) {
    return appointmentRepository.findById(id).map(this::toResponse);
  }

  @Override
  public Appointment update(final Appointment request) {
    final var appointmentFound =
        appointmentRepository
            .findById(request.getId())
            .orElseThrow(() -> new AppointmentNotFoundException(request.getId()));

    final var newEntity =
        AppointmentEntity.builder()
            .id(appointmentFound.getId())
            .schedule(appointmentFound.getSchedule())
            .customer(appointmentFound.getCustomer())
            .serviceTime(request.getServiceTime())
            .build();

    final var updated = appointmentRepository.save(newEntity);

    return this.toResponse(updated);
  }

  @Transactional
  @Override
  public void delete(final int id) {
    appointmentRepository.deleteById(id);
  }

  @Override
  public boolean existsByScheduleCustomerAndTime(
      final int scheduleId, final int customerId, final LocalDateTime serviceTime) {
    return appointmentRepository.existsByScheduleIdAndCustomerIdAndServiceTime(
        scheduleId, customerId, serviceTime);
  }

  private Appointment toResponse(final AppointmentEntity entity) {
    return new Appointment(
        entity.getId(),
        entity.getSchedule().getId(),
        entity.getCustomer().getId(),
        entity.getServiceTime());
  }
}
