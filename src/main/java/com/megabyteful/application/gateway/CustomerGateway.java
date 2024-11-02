package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Customer;
import java.util.Optional;

public interface CustomerGateway {

  Customer save(final Customer customer);

  Optional<Customer> findByCpf(final String cpf);

  Customer update(final Customer customer);

  void delete(final String cpf);
}
