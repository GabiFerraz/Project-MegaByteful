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

  public Customer execute(final Customer request) {
    final var customer = gateway.findByCpf(request.getCpf());

    if (customer.isPresent()) {
      throw new CustomerAlreadyExistsException(request.getName(), request.getCpf());
    }

    final var buildDomain =
        Customer.createCustomer(
            request.getName(),
            request.getCpf(),
            request.getPhone(),
            request.getEmail(),
            request.getAppointments());

    return gateway.save(buildDomain);
  }
}
