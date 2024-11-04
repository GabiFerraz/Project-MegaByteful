package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Service;
import com.megabyteful.application.gateway.ServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetService {

  private final ServiceGateway gateway;

  public Service execute(final int id) {
    return gateway
        .findById(id)
        .orElseThrow(
            () -> new com.megabyteful.application.usecase.exception.ServiceNotFoundException(id));
  }
}