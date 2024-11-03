package com.megabyteful.application.usecase;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Component;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.gateway.ServiceGateway;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetService {
	
	private final ServiceGateway gateway;
	  public Service execute(final String name, final Integer idProviderService) throws ServiceNotFoundException  {
		    return gateway.findByNameAndIdProviderService(name,idProviderService).orElseThrow(() -> new ServiceNotFoundException());
		  }

}
