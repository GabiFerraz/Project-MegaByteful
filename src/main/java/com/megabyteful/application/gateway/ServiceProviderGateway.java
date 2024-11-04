package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.ServiceProvider;
import java.util.Optional;

public interface ServiceProviderGateway {

  ServiceProvider save(final ServiceProvider serviceProvider);

  Optional<ServiceProvider> findByDocument(final String document);

  ServiceProvider update(final ServiceProvider serviceProvider);

  void delete(final String document);
}
