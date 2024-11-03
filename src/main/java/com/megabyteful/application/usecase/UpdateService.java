package com.megabyteful.application.usecase;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Component;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.dto.UpdateServiceRequest;
import com.megabyteful.application.gateway.ServiceGateway;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateService {
	
	private final ServiceGateway gateway;

	  public Service execute(final String name,final Integer idServiceProvider, final UpdateServiceRequest updateServiceRequest) throws ServiceNotFoundException {
	    final var serviceFound = gateway.findByNameAndIdProviderService(name,idServiceProvider).orElseThrow(() -> new ServiceNotFoundException());

	    serviceFound.setName(updateServiceRequest.getName());
	    serviceFound.setPrice(updateServiceRequest.getPrice());
	    serviceFound.setIdServiceProvider(updateServiceRequest.getIdServiceProvider());

	    return gateway.update(serviceFound);
	  }

}
