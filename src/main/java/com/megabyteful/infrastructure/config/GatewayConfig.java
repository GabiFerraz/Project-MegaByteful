package com.megabyteful.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.infrastructure.gateway.ServiceProviderGatewayImpl;
import com.megabyteful.infrastructure.persistence.repository.ServiceProviderJPARepository;

@Configuration
public class GatewayConfig {
	
	@Bean
	ServiceProviderGateway serviceProviderGateway(final ServiceProviderJPARepository serviceProviderJPARepository) {
		return new ServiceProviderGatewayImpl(serviceProviderJPARepository);
	}

}
