package com.megabyteful.application.usecase.fixture;

import com.megabyteful.application.domain.Customer;

public class CustomerTestFixture {

  public static Customer validCustomer() {
    return Customer.builder()
        .name("John Doe")
        .cpf("12345678901")
        .phone("12345678969")
        .email("test@example.com")
        .build();
  }

  public static Customer customerToUpdate() {
    return Customer.builder()
        .name("John Doe")
        .cpf("12345678901")
        .phone("98776576767")
        .email("testing@test.com")
        .build();
  }
}
