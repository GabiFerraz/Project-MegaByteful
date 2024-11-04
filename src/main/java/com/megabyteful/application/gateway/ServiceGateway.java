package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Service;
import java.util.Optional;

public interface ServiceGateway {
  Service save(Service service);

  Optional<Service> findById(final int id);

  Service update(final Service service);

  void delete(final int id);
}
