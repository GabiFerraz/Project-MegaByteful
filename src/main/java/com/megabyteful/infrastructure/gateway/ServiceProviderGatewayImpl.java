package com.megabyteful.infrastructure.gateway;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;
import com.megabyteful.infrastructure.persistence.entity.ServiceProviderEntity;
import com.megabyteful.infrastructure.persistence.repository.ServiceProviderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceProviderGatewayImpl implements ServiceProviderGateway {

	private final ServiceProviderRepository serviceProviderRepository;

	@Override
	public ServiceProvider save(final ServiceProvider serviceProvider) {
		final var entity = ServiceProviderEntity.builder()
				.name(serviceProvider.getName())
				.document(serviceProvider.getDocument())
				.phone(serviceProvider.getPhone())
				.beautyServices(serviceProvider.getBeautyServices())
				.address(serviceProvider.getAddress())
				.email(serviceProvider.getEmail())
				.build();

		final var saved = serviceProviderRepository.save(entity);

		return this.toResponse(saved);
	}

	@Override
	public Optional<ServiceProvider> findByDocument(String document) {
		return serviceProviderRepository.findByDocument(document).map(this::toResponse);
	}

	@Override
	public ServiceProvider update(ServiceProvider serviceProvider) {
		final var serviceProviderFound = serviceProviderRepository
				.findByDocument(serviceProvider.getDocument())
				.orElseThrow(() -> new ServiceProviderNotFoundException(serviceProvider.getDocument()));
		
		final var newEntity = ServiceProviderEntity.builder()
				.id(serviceProviderFound.getId())
				.name(serviceProviderFound.getName())
				.document(serviceProviderFound.getDocument())
				.phone(serviceProviderFound.getPhone())
				.beautyServices(serviceProviderFound.getBeautyServices())
				.address(serviceProviderFound.getAddress())
				.email(serviceProviderFound.getEmail())
				.build();

		final var update = serviceProviderRepository.save(newEntity);

		return this.toResponse(update);
	}

	@Transactional
	@Override
	public void delete(final String document) {
		serviceProviderRepository.deleteByDocument(document);
	}

	private ServiceProvider toResponse(final ServiceProviderEntity entity) {
		return new ServiceProvider(
				entity.getId(), 
				entity.getName(), 
				entity.getDocument(), 
				entity.getPhone(),
				entity.getBeautyServices(), 
				entity.getAddress(), 
				entity.getEmail());
	}

}
