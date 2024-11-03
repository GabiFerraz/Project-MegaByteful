package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.validCustomerRequest;
import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.validCustomerResponse;
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
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class CreateCustomerTest {

  private final CustomerGateway customerGateway = mock(CustomerGateway.class);
  private final CreateCustomer createCustomer = new CreateCustomer(customerGateway);

  @Test
  void shouldCreateCustomerSuccessfully() {
    final var request = validCustomerRequest();
    final var gatewayResponse = validCustomerResponse();

    when(customerGateway.findByCpf(request.getCpf())).thenReturn(Optional.empty());
    when(customerGateway.save(any(Customer.class))).thenReturn(gatewayResponse);

    final var createdCustomer = createCustomer.execute(request);

    assertThat(createdCustomer.getId()).isNotNull();
    assertThat(createdCustomer.getName()).isEqualTo(request.getName());
    assertThat(createdCustomer.getCpf()).isEqualTo(request.getCpf());
    assertThat(createdCustomer.getPhone()).isEqualTo(request.getPhone());
    assertThat(createdCustomer.getEmail()).isEqualTo(request.getEmail());
    assertThat(createdCustomer.getAppointments()).isEqualTo(List.of());

    verify(customerGateway).findByCpf(any());

    ArgumentCaptor<Customer> captorCustomer = forClass(Customer.class);

    verify(customerGateway).save(captorCustomer.capture());

    assertThat(captorCustomer.getValue().getId()).isNull();
    assertThat(captorCustomer.getValue().getName()).isEqualTo(request.getName());
    assertThat(captorCustomer.getValue().getCpf()).isEqualTo(request.getCpf());
    assertThat(captorCustomer.getValue().getPhone()).isEqualTo(request.getPhone());
    assertThat(captorCustomer.getValue().getEmail()).isEqualTo(request.getEmail());
    assertThat(captorCustomer.getValue().getAppointments()).isEqualTo(List.of());
  }

  @Test
  void shouldNotCreateCustomerWhenCpfAlreadyExists() {
    final var request = validCustomerRequest();
    final var gatewayResponse = validCustomerResponse();

    when(customerGateway.findByCpf(request.getCpf())).thenReturn(Optional.of(gatewayResponse));

    assertThatThrownBy(() -> createCustomer.execute(request))
        .isInstanceOf(CustomerAlreadyExistsException.class)
        .hasMessage("Customer [John Doe] with CPF [12345678901] already exists.");

    verify(customerGateway).findByCpf(any());
    verify(customerGateway, never()).save(request);
  }
}
