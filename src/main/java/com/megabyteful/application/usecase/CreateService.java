package com.megabyteful.application.usecase;

import org.springframework.stereotype.Component;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.gateway.ServiceGateway;
import com.megabyteful.application.usecase.exception.ServiceAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateService {
	
	private final ServiceGateway gateway;
	
	public Service execute(final Service request) {
		
		final var service = gateway.findByNameAndIdProviderService(request.getName(),request.getServiceProvider().getId()); 
		
	    if (service.isPresent()) {
	      throw new ServiceAlreadyExistsException();
	    }

	    final var buildDomain =
	        Service.createService(request.getName(),request.getPrice(),request.getSchedules(),request.getServiceProvider() );

	    return gateway.save(buildDomain);
	  }

}
