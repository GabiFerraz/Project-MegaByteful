package com.megabyteful.application.usecase;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Component;

import com.megabyteful.application.gateway.ServiceGateway;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteService {
	
	private final ServiceGateway gateway;
	
	public void execute(final String name, final Integer idProviderService) throws ServiceNotFoundException {
		final var service = gateway.findByNameAndIdProviderService(name, idProviderService).orElseThrow(() -> new ServiceNotFoundException());
		gateway.delete(service.getName(),service.getServiceProvider().getId());
	}

}


