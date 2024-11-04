package com.megabyteful.infrastructure.gateway;

import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.megabyteful.application.domain.Appointment;
import com.megabyteful.application.domain.Schedule;
import com.megabyteful.application.domain.Service;
import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import com.megabyteful.application.usecase.exception.ScheduleNotFoundException;
import com.megabyteful.infrastructure.persistence.entity.AppointmentEntity;
import com.megabyteful.infrastructure.persistence.entity.CustomerEntity;
import com.megabyteful.infrastructure.persistence.entity.ScheduleEntity;
import com.megabyteful.infrastructure.persistence.entity.ServiceEntity;
import com.megabyteful.infrastructure.persistence.entity.ServiceProviderEntity;
import com.megabyteful.infrastructure.persistence.repository.ScheduleRepository;
import com.megabyteful.infrastructure.persistence.repository.ServiceProviderRepository;
import com.megabyteful.infrastructure.persistence.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceGatewayImpl implements ServiceGateway {

	private final ServiceRepository serviceRepository;
	private final ServiceProviderRepository serviceProviderRepository;
	private final ScheduleRepository ScheduleRepository;
		
	 @Override
	  public Service save(final Service service,final Integer idServideProvider, final Integer idSchedule) {
		 
		 final Optional<ServiceProviderEntity> serviceProviderFound = serviceProviderRepository.findById(idServideProvider);
		 final var servideProvider = serviceProviderFound.get();
		 final Optional<List<ScheduleEntity>> scheduleFound = ScheduleRepository.findById(idSchedule);
		 final var schedule = scheduleFound.get();
		 
	    final var serviceEntity =
	        ServiceEntity.builder()
	            .name(service.getName())
	            .price(service.getPrice())
	            .schedules(schedule)
	            .serviceProvider(servideProvider)
	            .build();

	    final var saved = serviceRepository.save(serviceEntity);

	    return this.toResponse(saved);
	  }
	 
	  @Override
	  public Optional<Service> findByNameAndIdProviderService(final String name, Integer idProviderService) {
	    return serviceRepository.findByNameAndIdProviderService(name, idProviderService).map(this::toResponse);
	  }
	
	 private Service toResponse(final ServiceEntity entity) {
		    return new Service(
		    		entity.getId(),
		    		entity.getName(),
		    		entity.getPrice(),
		    		entity.getSchedules(),
		    		entity.getServiceProvider()
		    		);
		}
	 
	 
	  @Override
	  public Service update(final Service service) throws ServiceNotFoundException {
	    final var serviceFound = serviceRepository.findByNameAndIdProviderService(service.getName(),service.getIdServiceProvider())
	            		.orElseThrow(() -> new ServiceNotFoundException());
	    
	    final var newEntity = ServiceEntity.builder()
          .id(serviceFound.getId())
          .idProviderService(service.getIdServiceProvider())
          .name(service.getName())
          .price(service.getPrice())
          .build();
	    
	    final var updated = serviceRepository.save(newEntity);
	    
	        return this.toResponse(updated);
	  }
	  
	  @Transactional
	  @Override
	  public void delete(final String name, Integer idProviderService) {
	    serviceRepository.deleteByNameAndIdProviderService(name, idProviderService);
	  }
}