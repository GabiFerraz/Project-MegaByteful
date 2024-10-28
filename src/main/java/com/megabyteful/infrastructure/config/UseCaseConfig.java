package com.megabyteful.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.ServiceProviderCreateUseCase;
import com.megabyteful.application.usecase.ServiceProviderDeleteUseCase;
import com.megabyteful.application.usecase.ServiceProviderGetByIdUseCase;
import com.megabyteful.application.usecase.ServiceProviderUpdateUseCase;

@Configuration
public class UseCaseConfig {

	@Bean
	ServiceProviderCreateUseCase serviceProviderCreateUseCase(final ServiceProviderGateway serviceProviderGateway) {
		return new ServiceProviderCreateUseCase(serviceProviderGateway);
	}
	
	@Bean
	ServiceProviderUpdateUseCase serviceProviderUpdateUseCase(final ServiceProviderGateway serviceProviderGateway) {
        return new ServiceProviderUpdateUseCase(serviceProviderGateway);
    }

    @Bean
    ServiceProviderDeleteUseCase serviceProviderDeleteUseCase(final ServiceProviderGateway serviceProviderGateway) {
        return new ServiceProviderDeleteUseCase(serviceProviderGateway);
    }

    @Bean
    ServiceProviderGetByIdUseCase bookGetByIdUseCase(final ServiceProviderGateway serviceProviderGateway) {
        return new ServiceProviderGetByIdUseCase(serviceProviderGateway);
    }

   
}
