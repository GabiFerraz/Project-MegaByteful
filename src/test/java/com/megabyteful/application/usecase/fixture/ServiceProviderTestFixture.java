package com.megabyteful.application.usecase.fixture;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.dto.UpdateServiceProviderRequest;

public class ServiceProviderTestFixture {
	
	public static ServiceProvider validServiceProviderRequest() {
		return ServiceProvider.builder()
				.name("Maria Silva")
				.document("12345678900")
				.phone("11949496536")
				.beautyServices("Hair cut")
				.address("Rua Hum, 500")
				.email("maria.silva@gmail.com")
				.build();
	}

	public static ServiceProvider validServiceProviderResponse() {
		return ServiceProvider.builder()
				.id(1)
				.name("Maria Silva")
				.document("12345678900")
				.phone("11949496536")
				.beautyServices("Hair cut")
				.address("Rua Hum, 500")
				.email("maria.silva@gmail.com")
				.build();
	}
	
	public static UpdateServiceProviderRequest serviceProviderUpdateRequest() {
		return UpdateServiceProviderRequest.builder()
				.name("Maria Silva")
				.phone("11949496536")
				.beautyServices("Hair cut")
				.address("Rua Hum, 500")
				.email("maria.silva@gmail.com")
				.build();
				
	}
	
	public static ServiceProvider validUpdateServiceProviderResponse() {
		return ServiceProvider.builder()
				.id(1)
				.name("Maria Silva")
				.document("12345678900")
				.phone("11949496536")
				.beautyServices("Hair cut")
				.address("Rua Hum, 500")
				.email("maria.silva@gmail.com")
				.build();
	}
}
