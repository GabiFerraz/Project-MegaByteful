package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCustomer {

  private final CustomerGateway gateway;

  public Customer execute(final Customer customerDomain) {
    final var customer = gateway.findByCpf(customerDomain.getCpf());

    if (customer.isPresent()) {
      throw new CustomerAlreadyExistsException(customerDomain.getName(), customerDomain.getCpf());
    }

    final var buildDomain = Customer.createCustomer(
        customerDomain.getName(),
        customerDomain.getCpf(),
        customerDomain.getPhone(),
        customerDomain.getEmail());

    return gateway.save(buildDomain);
  }
}
