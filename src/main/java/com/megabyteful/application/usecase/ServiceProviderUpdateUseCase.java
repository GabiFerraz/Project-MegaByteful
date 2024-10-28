package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.domain.exception.NotFoundException;
import com.megabyteful.application.gateway.ServiceProviderGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceProviderUpdateUseCase {

	@NonNull
	private final ServiceProviderGateway serviceProviderGateway;
	
	public Output execute(final Input input) {
		final var serviceProvider = serviceProviderGateway.findById(input.id())
				.orElseThrow(() -> new NotFoundException("Service Provider not found."));
		serviceProviderGateway.update(serviceProvider);
		
		return Output.from(serviceProvider);
	}
	
public record Input(Long id, String name, String document, String phone, String beautyServices, String address, String email) {
		
	}
	
public record Output(Long id, String name, String document, String phone, String beautyServices, String address, String email  ) {
		
		public static Output from(ServiceProvider serviceProvider) {
			return new Output(serviceProvider.getId(), serviceProvider.getName(), serviceProvider.getDocument(), serviceProvider.getPhone(), serviceProvider.getBeautyServices(), serviceProvider.getAddress(), serviceProvider.getEmail());
		}
	}
}
