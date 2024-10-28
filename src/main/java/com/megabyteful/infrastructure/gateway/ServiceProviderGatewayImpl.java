package com.megabyteful.infrastructure.gateway;

import java.util.Optional;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.infrastructure.persistence.entity.ServiceProviderJPAEntity;
import com.megabyteful.infrastructure.persistence.repository.ServiceProviderJPARepository;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceProviderGatewayImpl implements ServiceProviderGateway {
	
	@NonNull
	private final ServiceProviderJPARepository serviceProviderJPARepository;
	
	@Override
	@Transactional
	public void deleteById(final Long id) {
		serviceProviderJPARepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public ServiceProvider create(final ServiceProvider newServiceProvider) {
		return save(newServiceProvider);
	}

	@Override
	public Optional<ServiceProvider> findById(Long id) {
		return serviceProviderJPARepository.findById(id).map(ServiceProviderJPAEntity::toServiceProvider);
	}

	
	@Override
	@Transactional
	public ServiceProvider update(ServiceProvider serviceProvider) {
		return save(serviceProvider);
	}
	
	private ServiceProvider save(final ServiceProvider serviceProvider) {
		return serviceProviderJPARepository.save(ServiceProviderJPAEntity.from(serviceProvider)).toServiceProvider();
	}

}
