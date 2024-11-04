package com.megabyteful.application.usecase.fixture;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.dto.UpdateCustomerRequest;
import java.util.List;

public class CustomerTestFixture {

  public static Customer validCustomerRequest() {
    return Customer.builder()
        .name("John Doe")
        .cpf("12345678901")
        .phone("12345678969")
        .email("test@example.com")
        .appointments(List.of())
        .build();
  }

  public static Customer validCustomerResponse() {
    return Customer.builder()
        .id(1)
        .name("John Doe")
        .cpf("12345678901")
        .phone("12345678969")
        .email("test@example.com")
        .appointments(List.of())
        .build();
  }

  public static UpdateCustomerRequest customerUpdateRequest() {
    return UpdateCustomerRequest.builder()
        .name("John Doe")
        .phone("98776576767")
        .email("testing@test.com")
        .build();
  }

  public static Customer validUpdatedCustomerResponse() {
    return Customer.builder()
        .id(1)
        .name("John Doe")
        .cpf("12345678901")
        .phone("98776576767")
        .email("testing@test.com")
        .appointments(List.of())
        .build();
  }
}
