package com.megabyteful.application.gateway;

import java.util.Optional;

import com.megabyteful.application.domain.ServiceProvider;

public interface ServiceProviderGateway {
	
	ServiceProvider save(ServiceProvider serviceProvider);
	
	Optional<ServiceProvider> findByDocument(String document);
	
	ServiceProvider update(ServiceProvider serviceProvider);
	
	void delete(String document);
	
}
