package com.megabyteful.application.gateway;

import java.util.Optional;

import com.megabyteful.application.domain.ServiceProvider;

public interface ServiceProviderGateway {
	
	ServiceProvider save(final ServiceProvider serviceProvider);
	
	Optional<ServiceProvider> findByDocument(final String document);
	
	ServiceProvider update(final ServiceProvider serviceProvider);
	
	void delete(final String document);
	
}
