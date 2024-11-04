package com.megabyteful.infrastructure.gateway;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.domain.Service;
import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceGateway;
import com.megabyteful.application.usecase.exception.ServiceNotFoundException;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;
import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import com.megabyteful.infrastructure.persistence.entity.ScheduleEntity;
import com.megabyteful.infrastructure.persistence.entity.ServiceEntity;
import com.megabyteful.infrastructure.persistence.entity.ServiceProviderEntity;
import com.megabyteful.infrastructure.persistence.repository.ScheduleRepository;
import com.megabyteful.infrastructure.persistence.repository.ServiceProviderRepository;
import com.megabyteful.infrastructure.persistence.repository.ServiceRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ServiceGatewayImpl implements ServiceGateway {

  private final ServiceRepository serviceRepository;
  private final ServiceProviderRepository serviceProviderRepository;
  private final ScheduleRepository scheduleRepository;

  @Override
  public Service save(final Service request) {

    final Optional<ServiceProviderEntity> serviceProviderFound =
        serviceProviderRepository.findById(request.getServiceProvider().getId());

    if (serviceProviderFound.isEmpty()) {
      throw new ServiceProviderNotFoundException(request.getServiceProvider().getId());
    }

    final var serviceProvider = serviceProviderFound.get();

    final var serviceEntity =
        ServiceEntity.builder()
            .name(request.getName())
            .price(request.getPrice())
            .schedules(List.of())
            .serviceProvider(serviceProvider)
            .build();

    final var saved = serviceRepository.save(serviceEntity);

    return this.toResponse(saved);
  }

  @Override
  public Optional<Service> findById(final int id) {
    return serviceRepository.findById(id).map(this::toResponse);
  }

  @Override
  public Service update(final Service request) {
    final var serviceFound =
        serviceRepository
            .findById(request.getId())
            .orElseThrow(() -> new ServiceNotFoundException(request.getId()));

    final var newEntity =
        ServiceEntity.builder()
            .id(serviceFound.getId())
            .name(request.getName())
            .price(request.getPrice())
            .schedules(serviceFound.getSchedules())
            .serviceProvider(serviceFound.getServiceProvider())
            .build();

    final var updated = serviceRepository.save(newEntity);

    return this.toResponse(updated);
  }

  @Transactional
  @Override
  public void delete(final int id) {
    serviceRepository.deleteById(id);
  }

  private Service toResponse(final ServiceEntity entity) {
    return new Service(
        entity.getId(),
        entity.getName(),
        entity.getPrice(),
        this.toScheduleDomain(entity.getSchedules()),
        this.toServiceProviderDomain(entity.getServiceProvider()));
  }

  private List<Schedule> toScheduleDomain(List<ScheduleEntity> scheduleEntities) {
    return scheduleEntities.stream()
        .map(
            scheduleEntity ->
                new Schedule(
                    scheduleEntity.getId(),
                    scheduleEntity.getService().getId(),
                    this.toAppointmentDomain(scheduleEntity.getAppointments()),
                    scheduleEntity.getServiceTime(),
                    scheduleEntity.getAvailableTimes()))
        .toList();
  }

  private List<Appointment> toAppointmentDomain(List<AppointmentEntity> appointmentEntities) {
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

  private ServiceProvider toServiceProviderDomain(final ServiceProviderEntity entity) {
    return new ServiceProvider(
        entity.getId(),
        entity.getName(),
        entity.getDocument(),
        entity.getPhone(),
        entity.getBeautyServices(),
        entity.getAddress(),
        entity.getEmail(),
        this.toServicesDomain(entity.getServices()));
  }

  private List<Service> toServicesDomain(List<ServiceEntity> serviceEntities) {
    return serviceEntities.stream().map(this::toResponse).toList();
  }
}
