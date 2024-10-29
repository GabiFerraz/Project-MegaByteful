package com.megabyteful.application.gateway;

import com.megabyteful.application.domain.Customer;

public interface MegaBytefulGateway {

    Customer save(Customer customer);
}
