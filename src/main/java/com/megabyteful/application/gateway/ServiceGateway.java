package com.megabyteful.application.gateway;

import java.util.Optional;

import javax.management.ServiceNotFoundException;

import com.megabyteful.application.domain.Service;

public interface ServiceGateway {
	
	Service save(final Service service);
	Optional<Service> findByNameAndIdProviderService(final String name, final Integer idServiceProvider);
	Service update (final Service service) throws ServiceNotFoundException;
	void delete (final String name,final Integer idService);
}