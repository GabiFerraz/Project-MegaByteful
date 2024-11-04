package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCustomer {

  private final CustomerGateway gateway;

  public Customer execute(final String cpf) {
    return gateway.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException(cpf));
  }
}
