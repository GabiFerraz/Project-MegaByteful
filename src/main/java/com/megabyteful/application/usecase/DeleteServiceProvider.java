package com.megabyteful.application.usecase;

import org.springframework.stereotype.Component;

import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteServiceProvider {

	private final ServiceProviderGateway gateway;
	
	public void execute(final String document) {
		final var serviceProvider = gateway.findByDocument(document).orElseThrow(()-> new ServiceProviderNotFoundException(document));
		
		gateway.delete(serviceProvider.getDocument());
	}
}
