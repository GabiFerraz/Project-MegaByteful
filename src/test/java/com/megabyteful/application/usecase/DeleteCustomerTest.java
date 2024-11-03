package com.megabyteful.application.usecase;

import static com.megabyteful.application.usecase.fixture.CustomerTestFixture.validCustomerResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.megabyteful.application.gateway.CustomerGateway;
import com.megabyteful.application.usecase.exception.CustomerNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class DeleteCustomerTest {

  private final CustomerGateway customerGateway = mock(CustomerGateway.class);
  private final DeleteCustomer deleteCustomer = new DeleteCustomer(customerGateway);

  @Test
  void shouldDeleteCustomerSuccessfully() {
    final var cpf = "12345678901";
    final var responseGateway = validCustomerResponse();

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.of(responseGateway));
    doNothing().when(customerGateway).delete(cpf);

    deleteCustomer.execute(cpf);

    verify(customerGateway).findByCpf(cpf);
    verify(customerGateway).delete(cpf);
  }

  @Test
  void shouldNotDeleteCustomerWhenCpfDoesNotExist() {
    final var cpf = "12345678901";

    when(customerGateway.findByCpf(cpf)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> deleteCustomer.execute(cpf))
        .isInstanceOf(CustomerNotFoundException.class)
        .hasMessage("Customer with CPF [12345678901] not found.");

    verify(customerGateway).findByCpf(cpf);
    verify(customerGateway, never()).delete(cpf);
  }
}
