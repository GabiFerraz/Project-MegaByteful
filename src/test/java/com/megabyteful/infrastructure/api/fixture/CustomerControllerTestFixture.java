package com.megabyteful.infrastructure.api.fixture;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.dto.UpdateCustomerRequest;

public class CustomerControllerTestFixture {

  public static Customer validCustomerRequest() {
    return Customer.builder()
        .name("John Doe")
        .cpf("12345678901")
        .phone("12345678969")
        .email("test@example.com")
        .build();
  }

  public static UpdateCustomerRequest validUpdateCustomerRequest() {
    return UpdateCustomerRequest.builder()
        .name("John Doe")
        .phone("12345678969")
        .email("test@example.com")
        .build();
  }

  public static Customer validCustomerResponse() {
    return Customer.builder()
        .id(1)
        .name("John Doe")
        .cpf("12345678901")
        .phone("12345678969")
        .email("test@example.com")
        .build();
  }
}
