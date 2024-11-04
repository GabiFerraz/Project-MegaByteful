package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.dto.UpdateCustomerRequest;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCustomer {

  private final CustomerGateway gateway;

  public Customer execute(final String cpf, final UpdateCustomerRequest updateCustomerRequest) {
    final var customerFound =
        gateway.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException(cpf));

    customerFound.setName(updateCustomerRequest.getName());
    customerFound.setPhone(updateCustomerRequest.getPhone());
    customerFound.setEmail(updateCustomerRequest.getEmail());

    return gateway.update(customerFound);
  }
}
