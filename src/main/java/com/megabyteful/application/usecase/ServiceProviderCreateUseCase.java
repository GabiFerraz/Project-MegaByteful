package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceProviderCreateUseCase {

	@NonNull
	private final ServiceProviderGateway serviceProviderGateway;
	
	public Output execute(final Input input) {
		final var newServiceProvider = ServiceProvider.newServiceProvider(input.name(), input.document(), input.phone(), input.beautyServices(), input.address(), input.email());
		final var serviceProvider = serviceProviderGateway.create(newServiceProvider);
		return Output.from(serviceProvider);
	}
	
	public record Input(String name, String document, String phone, String beautyServices, String address, String email) {
		
	}
	
	public record Output(Long id, String name, String document, String phone, String beautyServices, String address, String email) {
		
		public static Output from(ServiceProvider serviceProvider) {
			return new Output(serviceProvider.getId(), serviceProvider.getName(), serviceProvider.getDocument(), serviceProvider.getPhone(), serviceProvider.getBeautyServices(), serviceProvider.getAddress(), serviceProvider.getEmail());
		}
	}
}
