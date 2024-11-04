package com.megabyteful.application.usecase;

import com.megabyteful.application.gateway.ServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteService {

  private final ServiceGateway gateway;

  public void execute(final int id) {
    final var service =
        gateway
            .findById(id)
            .orElseThrow(
                () ->
                    new com.megabyteful.application.usecase.exception.ServiceNotFoundException(id));

    gateway.delete(service.getId());
  }
}
