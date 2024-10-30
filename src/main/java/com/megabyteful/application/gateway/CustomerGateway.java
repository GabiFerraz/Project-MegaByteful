package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Customer;

import java.util.Optional;

public interface CustomerGateway {

    Customer save(Customer customer);

    Optional<Customer> findByCpf(String cpf);

    Customer update(Customer customer);

    void delete(String cpf);
}
