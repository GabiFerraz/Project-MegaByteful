package com.megabyteful.application.usecase;

import org.springframework.stereotype.Component;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateServiceProvider {

	private final ServiceProviderGateway gateway;

	public ServiceProvider execute(final ServiceProvider request) {
		final var serviceProvider = gateway.findByDocument(request.getDocument());

		if (serviceProvider.isPresent()) {
			throw new ServiceProviderAlreadyExistsException(request.getName(),
					request.getDocument());
		}

		final var buildDomain = 
				ServiceProvider.createServiceProvider(
						request.getName(),
						request.getDocument(), 
						request.getPhone(),
						request.getBeautyServices(), 
						request.getAddress(),
						request.getEmail(), 
						request.getServices());

		return gateway.save(buildDomain);
	}

}
