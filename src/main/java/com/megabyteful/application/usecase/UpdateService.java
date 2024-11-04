package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.dto.UpdateServiceRequest;
import com.megabyteful.application.gateway.ServiceGateway;
import com.megabyteful.application.usecase.exception.ServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateService {

  private final ServiceGateway gateway;

  public Service execute(final int id, final UpdateServiceRequest updateServiceRequest) {
    final var serviceFound =
        gateway.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

    serviceFound.setName(updateServiceRequest.getName());
    serviceFound.setPrice(updateServiceRequest.getPrice());

    return gateway.update(serviceFound);
  }
}
