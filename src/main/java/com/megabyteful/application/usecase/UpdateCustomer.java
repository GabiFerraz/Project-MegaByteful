package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCustomer {

  private final CustomerGateway gateway;

  public Customer execute(final String cpf, final Customer customer) {
    final var customerFound =
        gateway.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException(cpf));

    customerFound.setName(customer.getName());
    customerFound.setPhone(customer.getPhone());
    customerFound.setEmail(customer.getEmail());

    return gateway.update(customerFound);
  }
}
