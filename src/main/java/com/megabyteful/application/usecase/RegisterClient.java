package com.megabyteful.application.usecase;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.MegaBytefulGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterClient {

    private final MegaBytefulGateway gateway;

    public Customer execute(final Customer customerDomain) {
        return gateway.save(customerDomain);
    }
}
