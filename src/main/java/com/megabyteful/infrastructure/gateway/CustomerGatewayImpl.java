package com.megabyteful.infrastructure.gateway;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import com.megabyteful.infrastructure.persistence.entity.CustomerEntity;
import com.megabyteful.infrastructure.persistence.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

  private final CustomerRepository customerRepository;

  @Override
  public Customer save(final Customer request) {

    final var entity =
        CustomerEntity.builder()
            .name(request.getName())
            .cpf(request.getCpf())
            .phone(request.getPhone())
            .email(request.getEmail())
            .appointments(List.of())
            .build();

    final var saved = customerRepository.save(entity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Customer> findByCpf(final String cpf) {
    return customerRepository.findByCpf(cpf).map(this::toResponse);
  }

  @Override
  public Customer update(final Customer request) {
    final var customerFound =
        customerRepository
            .findByCpf(request.getCpf())
            .orElseThrow(() -> new CustomerNotFoundException(request.getCpf()));

    final var newEntity =
        CustomerEntity.builder()
            .id(customerFound.getId())
            .name(request.getName())
            .cpf(customerFound.getCpf())
            .phone(request.getPhone())
            .email(request.getEmail())
            .appointments(customerFound.getAppointments())
            .build();

    final var updated = customerRepository.save(newEntity);

    return this.toResponse(updated);
  }

  @Transactional
  @Override
  public void delete(final String cpf) {
    customerRepository.deleteByCpf(cpf);
  }

  private Customer toResponse(final CustomerEntity entity) {
    return new Customer(
        entity.getId(),
        entity.getName(),
        entity.getCpf(),
        entity.getPhone(),
        entity.getEmail(),
        this.toAppointmentDomain(entity.getAppointments()));
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
