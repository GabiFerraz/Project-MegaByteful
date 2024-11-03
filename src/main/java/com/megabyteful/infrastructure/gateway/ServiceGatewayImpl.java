package com.megabyteful.infrastructure.gateway;

import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.gateway.ServiceGateway;
import com.megabyteful.infrastructure.persistence.entity.ServiceEntity;
import com.megabyteful.infrastructure.persistence.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceGatewayImpl implements ServiceGateway {

	private final ServiceRepository serviceRepository;
	
	 @Override
	  public Service save(final Service service) {
	    final var entity =
	        ServiceEntity.builder()
	            .name(service.getName())
	            .price(service.getPrice())
	            .idProviderService(service.getIdServiceProvider())
	            .build();

	    final var saved = serviceRepository.save(entity);

	    return this.toResponse(saved);
	  }
	 
	  @Override
	  public Optional<Service> findByNameAndIdProviderService(final String name, Integer idProviderService) {
	    return serviceRepository.findByNameAndIdProviderService(name, idProviderService).map(this::toResponse);
	  }
	
	 private Service toResponse(final ServiceEntity entity) {
		    return new Service(
					entity.getId(), entity.getIdProviderService(), entity.getName(), entity.getPrice());
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