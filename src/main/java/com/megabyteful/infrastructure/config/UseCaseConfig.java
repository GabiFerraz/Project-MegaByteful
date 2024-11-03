package com.megabyteful.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.CreateServiceProvider;

@Configuration
public class UseCaseConfig {

	@Bean
	CreateServiceProvider serviceProviderCreateUseCase(final ServiceProviderGateway serviceProviderGateway) {
		return new CreateServiceProvider(serviceProviderGateway);
	}
//	
//	@Bean
//	ServiceProviderUpdateUseCase serviceProviderUpdateUseCase(final ServiceProviderGateway serviceProviderGateway) {
//        return new ServiceProviderUpdateUseCase(serviceProviderGateway);
//    }
//
//    @Bean
//    ServiceProviderDeleteUseCase serviceProviderDeleteUseCase(final ServiceProviderGateway serviceProviderGateway) {
//        return new ServiceProviderDeleteUseCase(serviceProviderGateway);
//    }
//
//    @Bean
//    ServiceProviderGetByIdUseCase serviceProviderGetByIdUseCase(final ServiceProviderGateway serviceProviderGateway) {
//        return new ServiceProviderGetByIdUseCase(serviceProviderGateway);
//    }

   
}
