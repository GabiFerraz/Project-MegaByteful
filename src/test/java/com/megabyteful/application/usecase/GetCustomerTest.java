package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.validCustomerResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class GetCustomerTest {

  private final CustomerGateway customerGateway = mock(CustomerGateway.class);
  private final GetCustomer getCustomer = new GetCustomer(customerGateway);

  @Test
  void shouldGetCustomerSuccessfully() {
    final var cpf = "12345678901";
    final var responseGateway = validCustomerResponse();

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.of(responseGateway));

    final var customerFound = getCustomer.execute(cpf);

    assertThat(customerFound.getId()).isEqualTo(responseGateway.getId());
    assertThat(customerFound.getName()).isEqualTo(responseGateway.getName());
    assertThat(customerFound.getCpf()).isEqualTo(responseGateway.getCpf());
    assertThat(customerFound.getPhone()).isEqualTo(responseGateway.getPhone());
    assertThat(customerFound.getEmail()).isEqualTo(responseGateway.getEmail());

    verify(customerGateway).findByCpf(cpf);
  }

  @Test
  void shouldNotGetCustomerWhenCpfDoesNotExist() {
    final var cpf = "12345678901";

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> getCustomer.execute(cpf))
        .isInstanceOf(CustomerNotFoundException.class)
        .hasMessage("Customer with CPF [12345678901] not found.");

    verify(customerGateway).findByCpf(cpf);
  }
}
