package com.megabyteful.application.usecase;

import org.springframework.stereotype.Component;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.dto.UpdateServiceProviderRequest;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateServiceProvider {

	private final ServiceProviderGateway gateway;
	
	public ServiceProvider execute(final String document, final UpdateServiceProviderRequest updateServiceProviderRequest) {
		final var serviceProviderFound = gateway.findByDocument(document).orElseThrow(() -> 
		new ServiceProviderNotFoundException(document));
		
		serviceProviderFound.setName(updateServiceProviderRequest.getName());
		serviceProviderFound.setPhone(updateServiceProviderRequest.getPhone());
		serviceProviderFound.setBeautyServices(updateServiceProviderRequest.getBeautyServices());
		serviceProviderFound.setAddress(updateServiceProviderRequest.getAddress());
		serviceProviderFound.setEmail(updateServiceProviderRequest.getEmail());
		
		return gateway.update(serviceProviderFound);
		
	}
}
