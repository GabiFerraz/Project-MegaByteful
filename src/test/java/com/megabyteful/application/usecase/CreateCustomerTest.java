package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.validCustomer;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.domain.Customer;
import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerAlreadyExistsException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class CreateCustomerTest {

  private final CustomerGateway customerGateway = mock(CustomerGateway.class);
  private final CreateCustomer createCustomer = new CreateCustomer(customerGateway);

  @Test
  void shouldCreateCustomerSuccessfully() {
    final var customerRequest = validCustomer();
    final var gatewayResponse = validCustomer();

    when(customerGateway.findByCpf(customerRequest.getCpf())).thenReturn(Optional.empty());
    when(customerGateway.save(any(Customer.class))).thenReturn(gatewayResponse);

    final var createdCustomer = createCustomer.execute(customerRequest);

    assertThat(createdCustomer.getName()).isEqualTo(customerRequest.getName());
    assertThat(createdCustomer.getCpf()).isEqualTo(customerRequest.getCpf());
    assertThat(createdCustomer.getPhone()).isEqualTo(customerRequest.getPhone());
    assertThat(createdCustomer.getEmail()).isEqualTo(customerRequest.getEmail());

    verify(customerGateway).findByCpf(any());

    ArgumentCaptor<Customer> captorCustomer = forClass(Customer.class);

    verify(customerGateway).save(captorCustomer.capture());

    assertThat(captorCustomer.getValue().getName()).isEqualTo(customerRequest.getName());
    assertThat(captorCustomer.getValue().getCpf()).isEqualTo(customerRequest.getCpf());
    assertThat(captorCustomer.getValue().getPhone()).isEqualTo(customerRequest.getPhone());
    assertThat(captorCustomer.getValue().getEmail()).isEqualTo(customerRequest.getEmail());
  }

  @Test
  void shouldNotCreateCustomerWhenCpfAlreadyExists() {
    final var customerRequest = validCustomer();
    final var gatewayResponse = validCustomer();

    when(customerGateway.findByCpf(customerRequest.getCpf()))
        .thenReturn(Optional.of(gatewayResponse));

    assertThatThrownBy(() -> createCustomer.execute(customerRequest))
        .isInstanceOf(CustomerAlreadyExistsException.class)
        .hasMessage("Customer [John Doe] with CPF [12345678901] already exists.");

    verify(customerGateway).findByCpf(any());
    verify(customerGateway, never()).save(customerRequest);
  }
}
