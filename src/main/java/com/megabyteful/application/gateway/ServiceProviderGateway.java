package com.megabyteful.application.gateway;

import java.util.Optional;

import com.megabyteful.application.domain.ServiceProvider;

public interface ServiceProviderGateway {
	
	void deleteById(Long id);
	
	ServiceProvider create(ServiceProvider newServiceProvider);
	
	Optional<ServiceProvider> findById(Long id);
	
	//Optional<ServiceProvider> findAll(Long id);
	
	ServiceProvider update(ServiceProvider serviceProvider);

}
