package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.ServiceProvider;
import com.megabyteful.application.gateway.ServiceProviderGateway;
import com.megabyteful.application.usecase.exception.ServiceProviderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetServiceProvider {

  private final ServiceProviderGateway gateway;

  public ServiceProvider execute(final String document) {
    return gateway
        .findByDocument(document)
        .orElseThrow(() -> new ServiceProviderNotFoundException(document));
  }
}
