package com.megabyteful.application.usecase;

import com.megabyteful.application.gateway.ServiceProviderGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceProviderDeleteUseCase {

	@NonNull
	private final ServiceProviderGateway serviceProviderGateway;
	
	public void execute(final Long id) {
		serviceProviderGateway.deleteById(id);
	}
}
