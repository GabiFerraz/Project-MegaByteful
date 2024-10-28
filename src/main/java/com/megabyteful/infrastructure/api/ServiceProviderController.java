package com.megabyteful.infrastructure.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.megabyteful.api.ServiceProviderApi;
import com.megabyteful.application.usecase.ServiceProviderCreateUseCase;
import com.megabyteful.application.usecase.ServiceProviderDeleteUseCase;
import com.megabyteful.application.usecase.ServiceProviderGetByIdUseCase;
import com.megabyteful.application.usecase.ServiceProviderGetByIdUseCase.Output;
import com.megabyteful.application.usecase.ServiceProviderUpdateUseCase;
import com.megabyteful.model.CreateServiceProviderDTO;
import com.megabyteful.model.ServiceProviderDTO;
import com.megabyteful.model.UpdateServiceProviderDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ServiceProviderController implements ServiceProviderApi {

	@NonNull
	private final ServiceProviderCreateUseCase serviceProviderCreateUseCase;
	@NonNull
	private final ServiceProviderDeleteUseCase serviceProviderDeleteUseCase;
	@NonNull
	private final ServiceProviderGetByIdUseCase serviceProviderGetByIdUseCase;
	@NonNull
	private final ServiceProviderUpdateUseCase serviceProviderUpdateUseCase;

	@Override
	public ResponseEntity<ServiceProviderDTO> createServiceProvider(final CreateServiceProviderDTO body) {
		final var newServiceProvider = new ServiceProviderCreateUseCase.Input(body.getName(), body.getDocument(),
				body.getPhone(), body.getBeautyServices(), body.getAddress(), body.getEmail());
		final var createdServiceProvider = serviceProviderCreateUseCase.execute(newServiceProvider);
		final var uri = URI.create("/serviceProvider/" + createdServiceProvider.id());
		final var response = new ServiceProviderDTO().id(createdServiceProvider.id())
				.document(createdServiceProvider.document()).phone(createdServiceProvider.phone())
				.beautyServices(createdServiceProvider.beautyServices()).address(createdServiceProvider.address())
				.email(createdServiceProvider.email());
		return ResponseEntity.created(uri).body(response);
	}

	@Override
	public ResponseEntity<Void> deleteServiceProviderById(final Long id) {
		serviceProviderDeleteUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

	
	@Override
	public ResponseEntity<ServiceProviderDTO> getServiceProviderById(final Long id) {
		Output output = serviceProviderGetByIdUseCase.execute(id);
		final var response = new ServiceProviderDTO().id(output.id()).name(output.name()).document(output.document())
				.phone(output.phone()).beautyServices(output.beautyServices()).address(output.address())
				.email(output.email());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ServiceProviderDTO> updateServiceProviderById(final Long id, final UpdateServiceProviderDTO body) {
		final var input = new ServiceProviderUpdateUseCase.Input(id, body.getName(), body.getDocument(),
				body.getPhone(), body.getBeautyServices(), body.getAddress(), body.getEmail());
		final var output = serviceProviderUpdateUseCase.execute(input);
		final var response = new ServiceProviderDTO().id(output.id()).name(output.name()).document(output.document())
				.phone(output.phone()).beautyServices(output.beautyServices()).address(output.address())
				.email(output.email());
		return ResponseEntity.ok(response);
	}

}
