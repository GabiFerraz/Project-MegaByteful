package com.megabyteful.application.usecase;

import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCustomer {

  private final CustomerGateway gateway;

  public void execute(final String cpf) {
    final var customer =
        gateway.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException(cpf));

    gateway.delete(customer.getCpf());
  }
}
