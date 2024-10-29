package com.megabyteful.infrastructure.gateway;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.MegaBytefulGateway;
import com.megabyteful.infrastructure.persistence.entity.CustomerEntity;
import com.megabyteful.infrastructure.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MegaBytefulGatewayImpl implements MegaBytefulGateway {

    private final CustomerRepository customerRepository;

    @Override
    public Customer save(final Customer customer) {
        final CustomerEntity customerEntity = new CustomerEntity();

        final CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        return new Customer(
                savedCustomer.getName(),
                savedCustomer.getCpf(),
                savedCustomer.getPhone(),
                savedCustomer.getEmail());
    }
}
